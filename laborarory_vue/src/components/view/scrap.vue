<template>
  <div class="box">
    <n-config-provider :theme="null">
      <!-- 卡片视图区域 -->
      <n-card>
        <!-- 面包屑导航栏区域 -->
        <n-breadcrumb separator=">">
          <n-breadcrumb-item> home </n-breadcrumb-item>
          <n-breadcrumb-item> 报废记录 </n-breadcrumb-item>
        </n-breadcrumb>
        <h1>报废记录</h1>
        <!-- {{ editInfo.equipmentId }} -->
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
            <n-button type="primary" size="medium" @click="getScrapRecords"
              ><span class="iconfont icon-search"></span
            ></n-button>
            <n-button
              class="btn"
              type="primary"
              size="medium"
              @click="addScrapRecords"
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
import * as moment from 'moment'
import { h, defineComponent, toRef } from 'vue'
import { NTag, NButton, useMessage, useDialog } from 'naive-ui'
import api from "@/api/scrap.js"
// import { Icon } from '@vicons/utils' // vue3
import { reactive, ref } from 'vue'
import edit from '@/components/edit/editScrap.vue'
import add from '@/components/add/addScrap.vue'
import { storeToRefs } from 'pinia'
import { scrap } from '@/store/scrap'
import popConfirm from '@/components/pop.vue'
const createColumns = ({ updateScrapRecord, deleteScrapRecord }) => {
  return [
    {
      title: 'id',
      key: 'id'
    },
    {
      title: '设备名',
      key: 'name'
    },
    {
      title: '设备编号',
      key: 'equipmentId'
    },
    {
      title: '设备分类',
      key: 'category'
    },
    {
      title: '规格',
      key: 'specification'
    },
    {
      title: '认定人',
      key: 'handler'
    },
    {
      title: '报废原因',
      key: 'reason'
    },
    {
      title: '报废时间',
      key: 'scrapTime'
    },
    {
      title: '操作',
      key: 'actions',
      render(row) {
        return h('div', { id: 'button' }, [
          h(NButton, {
            size: "small",
            onClick: () => updateScrapRecord(row)
          }, { default: () => "修改" }),
          h(popConfirm, {
            size: "small",
            onClick: () => deleteScrapRecord(row.id)
          }, { default: () => "删除" })
        ]);
      }
    }
  ]
}

export default defineComponent({
  components: {
    edit, add
  },
  setup() {

    const message = useMessage()
    const dialog = useDialog()
    let scrapStore = scrap()
    let { editId, editInfo, addForm } = storeToRefs(scrapStore)
    const PageInfo = reactive({
      currentPage: 1,
      totalPages: 3
    })

    const selectFiledOptions = ref([
      {
        label: 'id',
        key: 'id'
      },
      {
        label: '设备名',
        key: 'name'
      },
      {
        label: '设备编号',
        key: 'equipmentId'
      },
      {
        label: '设备分类',
        key: 'category'
      },
      {
        label: '规格',
        key: 'specification'
      },
      {
        label: '认定人',
        key: 'handler'
      },
      {
        label: '报废时间',
        key: 'scrapTime'
      },
    ])
    const tableData = ref([
      {
        key: 0,
        id: 0,
        equipmentId: 193874,
        name: 'hg',
        category: 'hfeih',
        specification: 1000,
        handler: 'hf',
        reason: 'hif',
        scrapTime: 'hif'
      },
    ])
    let queryBody = reactive({
      currentPage: 1,
      type: '',
      text: ''
    })


    //获取设备列表
    const getScrapRecords = async () => {
      const { data: res } = await api.getScrapRecords(queryBody)
      tableData.value = res.data.records
      PageInfo.currentPage = res.data.currentPage
      PageInfo.totalPages = res.data.totalPages
    }

    //增加修理记录对话框
    const addScrapRecords = () => {
      dialog.success({
        title: "增加报废记录",
        content: () => {
          return h(
            add
          )
        },
        positiveText: "确定",
        onPositiveClick: async () => {
          console.log('here');

          // moment(addForm.value.repairTime).format('MMMM Do YYYY, h:mm:ss a')
          // console.log(addForm.value.repairTime);
          // addForm.value.repairTime = moment(addForm.value.repairTime).format('YYYY-MM-DD hh:mm:ss')
          const { data: res } = await api.addScrap(addForm.value)
          console.log(res);
          if (res.succeed) {
            message.success(res.message);
          } else {
            message.error(res.message);
          }
          getScrapRecords()
        }
      })
    }
    const changePage = (currentPage) => {
      queryBody.currentPage = currentPage
      getScrapRecords()
    }
    getScrapRecords()

    return {
      queryBody,
      tableData,
      editId,
      addForm,
      PageInfo,
      selectFiledOptions,
      columns: createColumns({
        updateScrapRecord(row) {
          editId.value = row.id
          dialog.success({
            title: "修改报废记录",
            content: () => {
              return h(
                edit
              )
            },
            positiveText: "确定",
            onPositiveClick: async () => {
              const { data: res } = await api.putScrapRecord(editId.value, editInfo.value)
              console.log(res);
              if (res.succeed) {
                message.success(res.message);
              } else {
                message.error(res.message);
              }
              getScrapRecords()
            }
          });
        },
        async deleteScrapRecord(id) {
          console.log(id);
          const { data: res } = await api.deleteScrapRecord(id)
          console.log(res);
          if (res.succeed) {
            message.success(res.message);
          } else {
            message.error(res.message);
          }
          getScrapRecords()
        }
      }),
      getScrapRecords,
      addScrapRecords,
      changePage
    }
  }
})
</script>

<style lang="less" scoped>
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