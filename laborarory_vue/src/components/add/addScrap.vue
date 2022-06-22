<template>
  <div class="card">
    <n-form :model="addForm">
      <n-form-item label="设备名">
        <n-input v-model:value="addForm.name" placeholder="设备名" />
      </n-form-item>
      <n-form-item label="编号">
        <n-input v-model:value="addForm.equipmentId" placeholder="编号" />
      </n-form-item>
      <n-form-item label="设备分类">
        <n-tree-select
          placeholder="设备分类"
          :options="selectCategoryOptions"
          v-model:value="addForm.category"
        />
      </n-form-item>
      <n-form-item label="报废原因">
        <n-input v-model:value="addForm.reason" placeholder="报废原因" />
      </n-form-item>
      <n-form-item label="认定人">
        <n-input v-model:value="addForm.handler" placeholder="认定人" />
      </n-form-item>
      <n-form-item label="报废时间">
        <n-input v-model:value="addForm.scrapTime" type="datetime" clearable />
      </n-form-item>
      <!-- <n-grid x-gap="12" :cols="6">
        <n-gi></n-gi>
        <n-gi></n-gi>
        <n-gi :offset="2"><n-button type="primary">保存</n-button></n-gi>
        <n-gi><n-button :offset="1">取消</n-button></n-gi>
      </n-grid> -->
    </n-form>
  </div>
</template>

<script>
import { reactive, ref } from '@vue/reactivity'
import api from '@/api/scrap'
import { storeToRefs } from 'pinia'
import { scrap } from '@/store/scrap'
export default {
  setup() {
    let scrapStore = scrap()
    let { addForm } = storeToRefs(scrapStore)
    const date = ref(118313526e4)
    // console.log(addForm.repairTime);
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
    return {
      addForm,
      date,
      selectCategoryOptions
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