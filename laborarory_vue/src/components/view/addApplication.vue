<template>
  <div class="box">
    <n-config-provider :theme="null">
      <!-- 卡片视图区域 -->
      <n-card>
        <!-- 面包屑导航栏区域 -->
        <n-breadcrumb separator=">">
          <n-breadcrumb-item> home </n-breadcrumb-item>
          <n-breadcrumb-item> 购买申请 </n-breadcrumb-item>
          <n-breadcrumb-item> 申请表 </n-breadcrumb-item>
        </n-breadcrumb>
        <h1>实验室新购设备申请表</h1>
        <n-form>
          <table class="hovertable">
            <tr>
              <td>设备名称</td>
              <td>
                <n-input v-model:value="addForm.name" placeholder="设备名称" />
              </td>
              <td>分类</td>
              <td>
                <n-tree-select
                  placeholder="设备分类"
                  :options="selectCategoryOptions"
                  v-model:value="addForm.category"
                />
              </td>
              <td>单价</td>
              <td>
                <n-input-number
                  v-model:value="addForm.price"
                  placeholder="单价"
                />
              </td>
            </tr>
            <tr>
              <td>规格</td>
              <td>
                <n-input
                  v-model:value="addForm.specification"
                  placeholder="规格"
                />
              </td>
              <td>生产厂商</td>
              <td>
                <n-input
                  v-model:value="addForm.manufacturer"
                  placeholder="生产厂商"
                />
              </td>
              <td>保修期</td>
              <td>
                <n-input
                  v-model:value="addForm.warrantyPeriod"
                  placeholder="保修期"
                />
              </td>
            </tr>
            <tr>
              <td rowspan="3">申请理由</td>
              <td colspan="3" rowspan="3">
                <n-input
                  v-model:value="addForm.reason"
                  placeholder="申请理由"
                  type="textarea"
                  size="small"
                  :autosize="{
                    minRows: 6,
                    maxRows: 6,
                  }"
                />
              </td>
              <td rowspan="3">申请人:</td>
              <td>
                <n-input
                  v-model:value="addForm.applicant"
                  placeholder="申请人"
                />
              </td>
            </tr>
            <tr>
              <td>年份</td>
            </tr>
            <tr>
              <td>日期</td>
            </tr>
            <tr>
              <td>领导意见</td>
              <td colspan="3"></td>
              <td>领导</td>
              <td>
                <n-input
                  v-model:value="addForm.approverName"
                  placeholder="领导"
                />
              </td>
            </tr>
            <tr>
              <td>备注</td>
            </tr>
          </table>
        </n-form>
        <div class="btn-box">
          <n-button type="primary" size="medium" @click="addApplication"
            >提交申请</n-button
          >
        </div>
      </n-card>
    </n-config-provider>
  </div>
</template>

<script>
import { useMessage } from 'naive-ui'
import api from "@/api/applications.js"
import { reactive, ref } from '@vue/reactivity'
import { useRouter } from 'vue-router'
export default {
  setup() {
    let router = useRouter()
    const message = useMessage()
    const addForm = reactive({
      category: null,
      name: null,
      manufacturer: null,
      warrantyPeriod: null,
      specification: null,
      price: null,
      applicant: null,
      approverName: null,
      reason: null,
      applyStatus: 0
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

    const addApplication = async () => {
      const { data: res } = await api.putApplication(addForm)
      // console.log(res);
      if (res.succeed) {
        message.success(res.message);
        router.push('/home/apply')
      } else {
        message.error(res.message);
      }
    }


    return {
      addApplication,
      addForm,
      selectCategoryOptions,
    }
  }

}
</script>

<style lang="less" scoped>
.box {
  width: 100%;
  height: 100%;
  background-color: #48484e;
  .n-card {
    margin: 30px auto;
    width: 95%;
    height: 95%;
    h1 {
      text-align: center;
    }
    table.hovertable {
      width: 98%;
      margin: 20px auto;
      background-color: #fff;
      font-size: 11px;
      color: #333333;
      border-width: 2px;
      border-color: #ebeef5;
      border-collapse: collapse;
    }

    table.common {
      width: 40%;
      margin: 20px 0;
    }

    .bold {
      font-weight: bold;
    }

    table.hovertable th {
      padding: 15px;
      /* border-style: solid; */
      border: 1px solid #ebeef5;
      color: #909399;
      font-size: 16px;
    }

    table.hovertable tr {
      border: 1px solid #ebeef5;
    }

    table.hovertable td {
      /* border-width: 1px; */
      padding: 15px;
      /* border-style: solid; */
      border: 1px solid #ebeef5;
      color: #606266;
      font-size: 14px;
    }

    table.hovertable td .btn {
      width: 30%;
      height: 30px;
      border-radius: 5px;
      background-color: #9aadc0;
      color: white;
      font-size: 14px;
    }

    table.hovertable td .btn:hover {
      opacity: 0.8;
    }

    table.hovertable td .btn:nth-child(2) {
      background-color: #d84766;
    }
  }
  .btn-box {
    width: 100%;
    display: flex;
    justify-content: flex-end;
  }
}
</style>