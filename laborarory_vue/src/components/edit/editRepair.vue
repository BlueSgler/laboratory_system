<template>
  <div class="card">
    <n-form :model="editInfo">
      <n-form-item label="设备名">
        <n-input v-model:value="editInfo.equipmentName" placeholder="设备名" />
      </n-form-item>
      <n-form-item label="编号">
        <n-input v-model:value="editInfo.equipmentId" placeholder="编号" />
      </n-form-item>
      <n-form-item label="修理厂家">
        <n-input
          v-model:value="editInfo.repairFactory"
          placeholder="修理厂家"
        />
      </n-form-item>
      <n-form-item label="负责人">
        <n-input v-model:value="editInfo.principal" placeholder="负责人" />
      </n-form-item>
      <n-form-item label="修理价格">
        <n-input v-model:value="editInfo.repairPrice" placeholder="修理价格" />
      </n-form-item>
      <n-form-item label="状态">
        <n-input v-model:value="editInfo.status" placeholder="状态" />
      </n-form-item>
      <n-form-item label="修理时间">
        <n-input v-model:value="editInfo.repairTime" placeholder="修理时间" />
      </n-form-item>
    </n-form>
  </div>
</template>

<script>
import { storeToRefs } from 'pinia'
import { repair } from '@/store/repair'
import { reactive, ref } from '@vue/reactivity'
import api from '@/api/repair'
export default {
  setup() {
    let repairStore = repair()
    let { editId, editInfo } = storeToRefs(repairStore)

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
      console.log('lala', editId.value);
    }

    const getRepairRecordById = async () => {
      const { data: res } = await api.getRepairRecordById(editId.value)
      if (res.succeed) {
        editInfo.value = res.data
        if (res.data.status) {
          editInfo.value.status = '已修好'
        } else {
          editInfo.value.status = '维修中'
        }
        console.log(editInfo.value);
      }
    }

    getRepairRecordById()
    return {
      editId,
      editInfo,
      selectCategoryOptions,
      test,
      getRepairRecordById
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