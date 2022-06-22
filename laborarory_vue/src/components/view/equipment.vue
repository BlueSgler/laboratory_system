<template>
  <div class="box">
    <n-config-provider :theme="null">
      <!-- 卡片视图区域 -->
      <n-card>
        <!-- 面包屑导航栏区域 -->
        <n-breadcrumb separator=">">
          <n-breadcrumb-item> home </n-breadcrumb-item>
          <n-breadcrumb-item> 设备管理 </n-breadcrumb-item>
        </n-breadcrumb>
        <h1>设备管理</h1>
        <div class="search">
          <n-form :model="queryBody">
            <!-- 设备状态 -->
            <n-form-item>
              <n-tree-select
                placeholder="设备状态"
                :options="selectStatusOptions"
                v-model:value="queryBody.status"
              />
            </n-form-item>
            <n-form-item>
              <n-tree-select
                placeholder="关键字段"
                :options="selectFiledOptions"
                v-model:value="queryBody.filed"
              />
            </n-form-item>
            <n-form-item>
              <n-input v-model:value="queryBody.value" placeholder="关键字" />
            </n-form-item>
            <n-form-item>
              <n-tree-select
                placeholder="设备分类"
                :options="selectCategoryOptions"
                v-model:value="queryBody.category"
              />
            </n-form-item>
            <n-form-item>
              <n-tree-select
                placeholder="排序字段"
                :options="selectOrderFiledOptions"
                v-model:value="queryBody.orderFiled"
              />
            </n-form-item>

            <n-form-item>
              <n-tree-select
                placeholder="排序规则"
                :options="selectOrderWayOptions"
                v-model:value="queryBody.orderWay"
              />
            </n-form-item>
            <!-- 搜索按钮 -->
            <n-button type="primary" size="medium" @click="getEquipments"
              ><span class="iconfont icon-search"></span
            ></n-button>
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
import api from "@/api/equipment.js"
// import { Icon } from '@vicons/utils' // vue3
import { reactive, ref } from 'vue'
import edit from '@/components/edit/editEquipment.vue'
import { storeToRefs } from 'pinia'
import { equipment } from '@/store/equipment'
const createColumns = ({ updateEquipment }) => {
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
      title: '型号',
      key: 'equipmentNo'
    },
    {
      title: '生产产家',
      key: 'manufacturer'
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
      title: '状态',
      key: 'status'
    },
    {
      title: '价格',
      key: 'price'
    },
    {
      title: '操作',
      key: 'actions',
      render(row) {
        return h(NButton, {
          size: "small",
          onClick: () => updateEquipment(row)
        }, { default: () => "修改" });
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
    let equipmentStore = equipment()
    const PageInfo = reactive({
      currentPage: 1,
      totalPages: 3
    })

    let { editId, editInfo } = storeToRefs(equipmentStore)


    const tableData = ref([
      {
        key: 0,
        id: 0,
        category: '软件工程实验室设备',
        equipmentNo: 193874,
        name: 'John Brown',
        manufacturer: 'hg',
        warrantyPeriod: 32,
        specification: 'hfeih',
        price: 1000,
        status: 1,
      },
    ])
    let queryBody = reactive({
      currentPage: 1,
      pageSize: 10,
      status: null,
      filed: null,
      value: null,
      category: null,
      orderFiled: null,
      orderWay: null
    })



    const selectStatusOptions = ref([
      {
        label: "设备正常",
        key: 0,
      },
      {
        label: "设备维修",
        key: 1,
      },
      {
        label: "设备报废",
        key: 2,
      },
    ])

    const selectFiledOptions = ref([
      {
        label: "id",
        key: "id",
      },
      {
        label: "设备编号",
        key: "equipment_no",
      },
      {
        label: "设备名称",
        key: "name",
      },
      {
        label: "生产产家",
        key: "manufacturer",
      },
      {
        label: "设备型号",
        key: "specification",
      },
      {
        label: "购买日期",
        key: "create_time",
      },
    ])

    const selectCategoryOptions = ref([
      {
        label: "软件工程实验室设备",
        key: "软件工程实验室设备"
      },
      {
        label: "服务工程与应用实验室设备",
        key: "服务工程与应用实验室设备"
      },
      {
        label: "嵌入式软件实验室设备",
        key: "嵌入式软件实验室设备"
      },
      {
        label: "云计算技术实验室设备",
        key: "云计算技术实验室设备"
      },

      {
        label: "软件可用性实验室设备",
        key: "软件可用性实验室设备"
      },
      {
        label: "移动互联网技术实验室设备",
        key: "移动互联网技术实验室设备"
      },
    ])

    const selectOrderFiledOptions = ref([
      {
        label: "价格",
        key: "price"
      },
      {
        label: "购买日期",
        key: "create_time"
      },
      {
        label: "保修期",
        key: "warranty_period"
      },
    ])

    const selectOrderWayOptions = ref([
      {
        label: "升序",
        key: 0
      },
      {
        label: "降序",
        key: 1
      },
    ])

    //获取设备列表
    const getEquipments = async () => {
      console.log(queryBody);
      const { data: res } = await api.getEquipments(queryBody)
      tableData.value = res.data.records
      for (let i = 0; i < res.data.records.length; i++) {
        if (res.data.records[i].status == 0) {
          console.log(res.data.records[i].status);
          tableData.value[i].status = '正常'
        } else if (res.data.records[i].status == 1) {
          tableData.value[i].status = '维修中'
        } else {
          tableData.value[i].status = '报废'
        }
      }
      PageInfo.currentPage = res.data.currentPage
      PageInfo.totalPages = res.data.totalPages
    }

    const changePage = (currentPage) => {
      queryBody.currentPage = currentPage
      getEquipments(queryBody)
    }

    getEquipments()

    return {
      queryBody,
      tableData,
      editId,
      editInfo,
      columns: createColumns({
        updateEquipment(row) {
          editId.value = row.id
          // console.log(editId.value);
          dialog.success({
            title: "修改设备信息",
            content: () => {
              return h(
                edit
              )
            },
            positiveText: "确定",
            onPositiveClick: async () => {

              const { data: res } = await api.putEquipment(editId.value, editInfo.value[0])
              if (res.succeed) {
                message.success(res.message);
                getEquipments()
              } else {
                message.error(res.message);
              }
            }
          });
        }
      }),
      selectStatusOptions,
      selectFiledOptions,
      selectCategoryOptions,
      selectOrderFiledOptions,
      selectOrderWayOptions,
      PageInfo,
      getEquipments,
      changePage,
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
        width: 16%;
        margin-right: 10px;
      }
    }
  }
  .n-pagination {
    margin-top: 20px;
  }
}
</style>