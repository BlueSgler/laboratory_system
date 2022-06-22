<template>
  <div class="card">
    <n-form :model="editInfo">
      <n-form-item>
        <n-tree-select
          :placeholder="`${defaultValue}`"
          :options="selectCategoryOptions"
          v-model:value="editInfo.category"
        />
      </n-form-item>
      <n-form-item label="名称">
        <n-input v-model:value="editInfo.name" placeholder="名称" />
      </n-form-item>
      <n-form-item label="生产厂家">
        <n-input v-model:value="editInfo.manufacturer" placeholder="生产厂家" />
      </n-form-item>
      <n-form-item label="保修期">
        <n-input v-model:value="editInfo.warrantyPeriod" placeholder="保修期" />
      </n-form-item>
      <n-form-item label="规格">
        <n-input v-model:value="editInfo.specification" placeholder="规格" />
      </n-form-item>
      <n-form-item label="价格">
        <n-input v-model:value="editInfo.price" placeholder="价格" />
      </n-form-item>
      <n-form-item label="申请理由">
        <n-input v-model:value="editInfo.reason" placeholder="申请理由" />
      </n-form-item>
      <n-form-item label="申请人">
        <n-input v-model:value="editInfo.applicant" placeholder="申请人" />
      </n-form-item>
      <n-form-item label="审批人">
        <n-input v-model:value="editInfo.approverName" placeholder="审批人" />
      </n-form-item>
      <n-form-item label="申请状态">
        <n-input v-model:value="editInfo.applyStatus" placeholder="申请状态" />
      </n-form-item>
    </n-form>
  </div>
</template>

<script>
import { storeToRefs } from 'pinia'
import { application } from '@/store/application'
import { reactive, ref } from '@vue/reactivity'
import api from '@/api/applications'
export default {
  setup() {
    let applicationStore = application()
    let { editId, editInfo } = storeToRefs(applicationStore)
    const defaultValue = ref('fieh')

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

    let queryBody = reactive({
      currentPage: 1,
      type: 'id',
      text: null,
    })
    const test = () => {
      // console.log('lala', editInfo.value.id);
    }

    const getApplicationById = async () => {
      queryBody.text = editInfo.value.id
      const { data: res } = await api.getApplications(queryBody)
      console.log(res);
      if (res.succeed) {
        editInfo.value = res.data.records[0]
        defaultValue.value = res.data.records[0].category
        console.log(editInfo.value);
      }
    }
    getApplicationById()
    // getRepairRecordById()
    return {
      editId,
      editInfo,
      selectCategoryOptions,
      test,
      getApplicationById,
      defaultValue
      // getRepairRecordById
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