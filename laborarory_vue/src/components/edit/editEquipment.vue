<template>
  <div class="card">
    <n-form :model="form.data">
      <n-form-item>
        <n-tree-select
          placeholder="设备分类"
          :options="selectCategoryOptions"
          v-model:value="form.data.category"
        />
      </n-form-item>
      <n-form-item label="设备名">
        <n-input v-model:value="form.data.name" placeholder="设备名" />
      </n-form-item>
      <n-form-item label="生产厂家">
        <n-input
          v-model:value="form.data.manufacturer"
          placeholder="生产厂家"
        />
      </n-form-item>
      <n-form-item label="保修期">
        <n-input
          v-model:value="form.data.warrantyPeriod"
          placeholder="保修期"
        />
      </n-form-item>
      <n-form-item label="型号">
        <n-input v-model:value="form.data.specification" placeholder="型号" />
      </n-form-item>
      <n-form-item label="价格">
        <n-input v-model:value="form.data.price" placeholder="价格" />
      </n-form-item>
    </n-form>
  </div>
</template>

<script>
import { storeToRefs } from 'pinia'
import { equipment } from '@/store/equipment'
import { reactive, ref } from '@vue/reactivity'
import api from '@/api/equipment.js'
export default {
  setup() {
    let equipmentStore = equipment()
    let { editId, editInfo } = storeToRefs(equipmentStore)

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

    const form = reactive({
      data: {
        category: "",
        name: "",
        manufacturer: "",
        warrantyPeriod: "",
        specification: "",
        price: ""
      }
    })

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
    const test = () => {
      console.log(editId.value);
      // getEditInfo()
    }
    const getEditInfo = async () => {
      queryBody.filed = "id"
      queryBody.value = editId.value
      const { data: res } = await api.getEquipments(queryBody)
      console.log(res);
      if (res.succeed) {
        editInfo.value = res.data.records
        form.data = editInfo.value[0]
        console.log('hh', form.data.category);
        // log
      }
    }

    getEditInfo()
    return {
      editId,
      editInfo,
      selectCategoryOptions,
      queryBody,
      form,
      test,
      getEditInfo
    }
  }

}
</script>

<style lang="less" scoped>
.card {
  width: 390px;
  background-color: #000;
  .n-form {
    .n-form-item {
      .btn-box {
        display: flex;
        justify-content: center;
      }
    }
  }
}
</style>