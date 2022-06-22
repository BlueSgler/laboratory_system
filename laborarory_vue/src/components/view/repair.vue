<template>
  <div class="box">
    <n-config-provider :theme="null"
      ><!-- 卡片视图区域 -->
      <n-card>
        <!-- 面包屑导航栏区域 -->
        <n-breadcrumb separator=">">
          <n-breadcrumb-item> home </n-breadcrumb-item>
          <n-breadcrumb-item> 修理记录 </n-breadcrumb-item>
        </n-breadcrumb>
        <h1>修理记录</h1>
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
            <n-button type="primary" size="medium" @click="getRepairRecords">
              <span class="iconfont icon-search"></span>
            </n-button>
            <n-button
              class="btn"
              type="primary"
              size="medium"
              @click="addRepairRecords"
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
        /> </n-card
    ></n-config-provider>
  </div>
</template>


<script>
import { h, defineComponent, toRef } from 'vue'
import { NTag, NButton, useMessage, useDialog } from 'naive-ui'
import api from "@/api/repair.js"
// import { Icon } from '@vicons/utils' // vue3
import { reactive, ref } from 'vue'
import edit from '@/components/edit/editRepair.vue'
import add from '@/components/add/addRepair.vue'
import { storeToRefs } from 'pinia'
import { repair } from '@/store/repair'
import popConfirm from '@/components/pop.vue'
const createColumns = ({ updateRepairRecord, deleteRepairRecord }) => {
  return [
    {
      title: 'id',
      key: 'id'
    },
    {
      title: '设备名',
      key: 'equipmentName'
    },
    {
      title: '设备编号',
      key: 'equipmentId'
    },
    {
      title: '修理厂家',
      key: 'repairFactory'
    },
    {
      title: '修理费用',
      key: 'repairPrice'
    },
    {
      title: '负责人',
      key: 'principal'
    },
    {
      title: '修理日期',
      key: 'repairTime'
    },
    {
      title: "状态",
      key: "status",
    },
    {
      title: '操作',
      key: 'actions',
      render(row) {
        return h('div', { id: 'button' }, [
          h(NButton, {
            size: "small",
            onClick: () => updateRepairRecord(row)
          }, { default: () => "修改" }),
          h(popConfirm, {
            size: "small",
            onClick: () => deleteRepairRecord(row.id)
          }, { default: () => "删除" })
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

    const message = useMessage()
    const dialog = useDialog()
    let repairStore = repair()
    let { editId, editInfo, addForm } = storeToRefs(repairStore)
    const PageInfo = reactive({
      currentPage: 1,
      totalPages: 3
    })

    const selectFiledOptions = ref([
      {
        label: "id",
        key: "id",
      },
      {
        label: "设备编号",
        key: "equipmentId",
      },
      {
        label: "设备名称",
        key: "equipmentName",
      },
      {
        label: "修理厂家",
        key: "repairFactory",
      },
      {
        label: "负责人",
        key: "principal",
      },
      {
        label: "修理价格",
        key: "repairPrice",
      },
      {
        label: "修理时间",
        key: "repairTime",
      },
      {
        label: "状态",
        key: "status",
      }
    ])
    const tableData = ref([
      {
        key: 0,
        id: 0,
        equipmentId: 193874,
        repairFactory: 'hg',
        specification: 'hfeih',
        repairPrice: 1000,
        principal: 1,
        status: 0
      },
    ])
    let queryBody = reactive({
      currentPage: 1,
      type: '',
      text: ''
    })


    //获取修理记录列表
    const getRepairRecords = async () => {
      console.log(queryBody);
      const { data: res } = await api.getRepairRecords(queryBody)
      // console.log(res.data.records[0].status);
      tableData.value = res.data.records
      for (let i = 0; i < tableData.value.length; i++) {
        console.log(tableData.value[i].status);
        // console.log(tableData.value)
        if (!tableData.value.status) {
          // console.log(res.data.records[i].status);
          tableData.value[i].status = '修理中'
        } else if (res.data.records[i].status) {
          tableData.value[i].status = '已修好'
        }
      }
      console.log(tableData.value)

      PageInfo.currentPage = res.data.currentPage
      PageInfo.totalPages = res.data.totalPages

    }

    //增加修理记录对话框
    const addRepairRecords = () => {
      dialog.success({
        title: "增加修理记录",
        content: () => {
          return h(
            add
          )
        },
        positiveText: "确定",
        onPositiveClick: async () => {
          const { data: res } = await api.addRepair(addForm.value)
          console.log(res);
          if (res.succeed) {
            message.success(res.message);
            addForm.value.equipmentId = ''
            addForm.value.equipmentName = ''
            addForm.value.principal = ''
            addForm.value.repairFactory = ''
            addForm.value.repairPrice = null
            addForm.value.repairTime = ''

          } else {
            message.error(res.message);
          }
          getRepairRecords()
        }
      })
    }

    const changePage = (currentPage) => {
      queryBody.currentPage = currentPage
      getRepairRecords()
    }
    getRepairRecords()

    return {
      queryBody,
      tableData,
      editId,
      addForm,
      PageInfo,
      selectFiledOptions,
      columns: createColumns({
        updateRepairRecord(row) {
          editId.value = row.id
          dialog.success({
            title: "修改修理记录",
            content: () => {
              return h(
                edit
              )
            },
            positiveText: "确定",
            onPositiveClick: async () => {
              const { data: res } = await api.putRepairRecord(editId.value, editInfo.value)
              console.log(res);
              if (res.succeed) {
                message.success(res.message);
              } else {
                message.error(res.message);
              }
              getRepairRecords()
            }
          });
        },
        async deleteRepairRecord(id) {
          console.log(id);
          const { data: res } = await api.deleteRepairRecord(id)
          console.log(res);
          if (res.succeed) {
            message.success(res.message);
          } else {
            message.error(res.message);
          }
          getRepairRecords()
        }
      }),
      getRepairRecords,
      addRepairRecords,
      changePage
    }
  }
})
</script>

<style lang="less" scoped>
.box {
  width: 100%;
  height: 100%;
  background-color: linear-gradient(to right, #0f0c29, #302b63, #24243e);
  .n-card {
    margin: 30px auto;
    width: 95%;
    height: 95%;
    background-color: #fff;
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