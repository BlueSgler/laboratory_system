<template>
  <div class="box">
    <n-config-provider :theme="null">
      <!-- 卡片视图区域 -->
      <n-card>
        <!-- 面包屑导航栏区域 -->
        <n-breadcrumb separator=">">
          <n-breadcrumb-item> home </n-breadcrumb-item>
          <n-breadcrumb-item> 购买申请 </n-breadcrumb-item>
        </n-breadcrumb>
        <h1>购买申请</h1>
        <div class="search">
          <n-form :model="queryBody">
            <n-form-item>
              <n-tree-select
                placeholder="关键字段"
                :options="selectFiledOptions"
                v-model:value="queryBody.type"
              />
            </n-form-item>
            <n-form-item>
              <n-input v-model:value="queryBody.text" placeholder="关键字" />
            </n-form-item>
            <!-- 搜索按钮 -->
            <n-button type="primary" size="medium" @click="getApplications"
              ><span class="iconfont icon-search"></span
            ></n-button>
            <n-button
              class="btn"
              type="primary"
              size="medium"
              @click="addApplication"
            >
              <span class="iconfont icon-add"></span>
            </n-button>
          </n-form>
        </div>
        <n-data-table size="small" :columns="columns" :data="tableData" />
        <n-pagination
          v-model:page="PageInfo.currentPage"
          :page-count="PageInfo.totalPages"
          :on-update:page="changePage"
        />
      </n-card>
    </n-config-provider>
  </div>
</template>


<script>
import { h, defineComponent, toRef } from 'vue'
import { NTag, NButton, useMessage, useDialog } from 'naive-ui'
import api from "@/api/applications.js"
import { reactive, ref } from 'vue'
import edit from '@/components/edit/editApplication.vue'
import popConfirm from '@/components/pop.vue'
import { storeToRefs } from 'pinia'
import { application } from '@/store/application'
import { useRouter } from 'vue-router'
const createColumns = ({ updateApplication, deleteApplications, DownloadApplication }) => {
  return [
    {
      title: 'id',
      key: 'id'
    },
    {
      title: '分类',
      key: 'category'
    },
    {
      title: '名称',
      key: 'name'
    },
    {
      title: '保修期',
      key: 'warrantyPeriod'
    },
    {
      title: '设备规格',
      key: 'specification'
    },
    {
      title: '申请人',
      key: 'applicant'
    },
    {
      title: '价格预算',
      key: 'price'
    },
    {
      title: '审批状态',
      key: 'applyStatus'
    },
    {
      title: '审批人',
      key: 'approverName'
    },
    {
      title: '操作',
      key: 'actions',
      render(row) {
        return h('div', { id: 'button' }, [
          h(NButton, {
            size: "small",
            onClick: () => updateApplication(row)
          }, { default: () => "修改" }),
          h(popConfirm, {
            size: "small",
            onClick: () => deleteApplications(row.id)
          }, { default: () => "删除" }),
          h(NButton, {
            size: "small",
            onClick: () => DownloadApplication(row.id)
          }, { default: () => "下载" }),
        ]);
      }

    }
  ]
}

export default defineComponent({
  components: {
    edit
  },
  setup() {
    let router = useRouter()
    const message = useMessage()
    const dialog = useDialog()
    let applicationStore = application()
    let { editId, editInfo } = storeToRefs(applicationStore)
    const PageInfo = reactive({
      currentPage: 1,
      totalPages: 3
    })


    const tableData = ref([
      {
        key: 0,
        id: 0,
        category: '',
        category: '软件工程实验室设备',
        equipName: 'John Brown',
        equipmentNo: 193874,
        approverName: 'hess',
        manufacturer: 'hg',
        warrantyPeriod: 32,
        specification: 'h2feih',
        price: 1000,
        applyStatus: 1,
        applicant: "hess"
      },
    ])
    let queryBody = reactive({
      currentPage: 1,
      type: '',
      text: ''
    })

    const selectFiledOptions = ref([
      {
        label: "id",
        key: "id",
      },
      {
        label: "分类",
        key: "category",
      },
      {
        label: "设备名称",
        key: "equipName",
      },
      {
        label: "规格",
        key: "specification",
      },
      {
        label: "审批状态",
        key: "applyStatus",
      },
      {
        label: "价格",
        key: "price",
      },
    ])
    //获取申请列表
    const getApplications = async () => {
      console.log(queryBody);
      const { data: res } = await api.getApplications(queryBody)
      tableData.value = res.data.records
      for (let i = 0; i < res.data.records.length; i++) {
        if (res.data.records[i].applyStatus == 0) {
          // console.log(res.data.records[i].status);
          tableData.value[i].applyStatus = '未审批'
        } else if (res.data.records[i].applyStatus == 1) {
          tableData.value[i].applyStatus = '已通过'
        }
      }
      PageInfo.currentPage = res.data.currentPage
      PageInfo.totalPages = res.data.totalPages
    }
    const addApplication = () => {
      console.log('here');
      router.push("/home/table")
    }

    const changePage = (currentPage) => {
      queryBody.currentPage = currentPage
      getApplications()
    }


    getApplications()

    return {
      queryBody,
      tableData,
      editId,
      editInfo,
      PageInfo,
      columns: createColumns({
        updateApplication(row) {
          editInfo.value.id = row.id
          dialog.success({
            title: "修改设备信息",
            content: () => {
              return h(
                edit
              )
            },
            positiveText: "确定",
            onPositiveClick: async () => {

              const { data: res } = await api.putApplication(editInfo.value)

              console.log(res);

              if (res.succeed) {
                message.success(res.message);
              } else {
                message.error(res.message);
              }
              getApplications()
            }
          });
        },
        async deleteApplications(id) {
          console.log(id);
          const { data: res } = await api.deleteApplication(id)
          console.log(res);
          if (res.succeed) {
            message.success(res.message);
          } else {
            message.error(res.message);
          }
          getApplications()
        },
        async DownloadApplication(id) {
          const res = await api.downLoadAppliacaion(id)
          console.log(res);
          let fileURL = window.URL.createObjectURL(new Blob([res.data]));
          let fileLink = document.createElement('a');

          fileLink.href = fileURL;
          fileLink.setAttribute('download', '申请表.docx');
          document.body.appendChild(fileLink);

          fileLink.click();
        }

      }),
      pagination: {
        pageSize: 10
      },
      selectFiledOptions,
      getApplications,
      addApplication,
      changePage
    }
  }
})
</script>

<style lang="less" scoped>
::v-deep #deleteBtn:hover {
  // border: 1px solid rgb(249, 56, 56) !important;
}
.box {
  width: 100%;
  height: 100%;
  background-color: #1e1e1e;
  .n-card {
    margin: 30px auto;
    width: 95%;
    height: 95%;
  }
  h1 {
    // color: pink;
  }
  .search {
    width: 100%;
    .n-form {
      display: flex;
      align-items: center;
      .n-form-item {
        width: 14%;
        margin-right: 10px;
      }
      .btn {
        margin-left: 10px;
      }
    }
  }
}

.n-pagination {
  margin-top: 20px;
}
</style>