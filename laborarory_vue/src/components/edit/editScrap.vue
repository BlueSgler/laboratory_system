<template>
  <div class="card">
    <n-form :model="editInfo">
      <n-form-item label="设备名">
        <n-input v-model:value="editInfo.name" placeholder="设备名" />
      </n-form-item>
      <n-form-item label="编号">
        <n-input v-model:value="editInfo.equipmentId" placeholder="编号" />
      </n-form-item>
      <n-form-item label="分类">
        <n-tree-select
          :placeholder="`${defaultValue}`"
          :options="selectCategoryOptions"
          v-model:value="editInfo.category"
        />
      </n-form-item>
      <n-form-item label="报废原因">
        <n-input v-model:value="editInfo.reason" placeholder="报废原因" />
      </n-form-item>
      <n-form-item label="认定人">
        <n-input v-model:value="editInfo.handler" placeholder="认定人" />
      </n-form-item>
      <n-form-item label="报废时间">
        <n-input v-model:value="editInfo.scrapTime" type="datetime" clearable />
      </n-form-item>
    </n-form>
  </div>
</template>

<script>
import { storeToRefs } from 'pinia'
import { scrap } from '@/store/scrap'
import { reactive, ref } from '@vue/reactivity'
import api from '@/api/scrap'
export default {
  setup() {
    const defaultValue = ref('fieh')
    let scrapStore = scrap()
    let { editId, editInfo } = storeToRefs(scrapStore)

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

    const getScrapRecordById = async () => {
      const { data: res } = await api.getScrapRecordById(editId.value)
      // console.log(res);
      if (res.succeed) {
        editInfo.value = res.data
        defaultValue.value = res.data.category
        console.log(editInfo.value);
      }
    }

    getScrapRecordById()
    return {
      editId,
      editInfo,
      selectCategoryOptions,
      selectCategoryOptions,
      defaultValue,
      test,
      getScrapRecordById
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