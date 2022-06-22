<template>
  <div class="card">
    <n-form :model="addForm">
      <n-form-item label="设备名">
        <n-input v-model:value="addForm.equipmentName" placeholder="设备名" />
      </n-form-item>
      <n-form-item label="编号">
        <n-input v-model:value="addForm.equipmentId" placeholder="编号" />
      </n-form-item>
      <n-form-item label="修理厂家">
        <n-input v-model:value="addForm.repairFactory" placeholder="修理厂家" />
      </n-form-item>
      <n-form-item label="负责人">
        <n-input v-model:value="addForm.principal" placeholder="负责人" />
      </n-form-item>
      <n-form-item label="修理价格">
        <n-input-number
          v-model:value="addForm.repairPrice"
          placeholder="修理价格"
        />
      </n-form-item>
      <n-form-item label="修理时间">
        <n-input v-model:value="addForm.repairTime" type="datetime" clearable />
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
import api from '@/api/repair'
import { storeToRefs } from 'pinia'
import { repair } from '@/store/repair'
export default {
  setup() {
    let repairStore = repair()
    let { addForm } = storeToRefs(repairStore)
    const date = ref(118313526e4)
    console.log(addForm.repairTime);
    return {
      addForm,
      date
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