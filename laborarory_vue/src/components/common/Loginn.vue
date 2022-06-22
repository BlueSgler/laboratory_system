<template>
  <n-config-provider :theme="null">
    <div class="box auto-spaced-linear-gradient">
      <div class="login-box">
        <div class="img-box">
          <img src="@/assets/logo.png" alt="" />
        </div>
        <h2>实验室设备管理系统</h2>
        <n-form v-model="loginForm">
          <n-input
            v-model:value="loginForm.username"
            type="text"
            placeholder="账号"
            :maxlength="8"
            :style="{ width: '80%' }"
          />
          <n-input
            v-model:value="loginForm.password"
            type="password"
            show-password-on="mousedown"
            placeholder="密码"
            :maxlength="8"
            :style="{ width: '80%' }"
          />
        </n-form>
        <div class="btn-box">
          <n-button type="primary" size="medium" @click="tologin"
            >登录</n-button
          >
          <n-button type="primary" size="medium" @click="reset">重置</n-button>
        </div>
      </div>
    </div>
  </n-config-provider>
</template>

<script>
import { storeToRefs } from 'pinia'
import { login } from '@/store/login'
import { useRouter } from 'vue-router'
import { useMessage } from 'naive-ui'
import api from '@/api/login'
export default {
  setup() {
    let loginStore = login()
    let { loginForm } = storeToRefs(loginStore)
    let router = useRouter()
    const message = useMessage()

    const tologin = async () => {
      const { data: res } = await api.login(loginForm.value)
      console.log(res.data);
      if (res.succeed) {
        window.sessionStorage.setItem('token', res.data)
        router.push("/home/index")
        message.success(res.message);
      } else {
        message.error(res.message);
      }
    }

    const reset = () => {
      loginForm.value.password = ''
      loginForm.value.username = ''
    }

    return {
      loginForm,
      tologin,
      reset
    }
  }
}

</script>

<style lang="less" scoped>
.box {
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  .login-box {
    border-radius: 4px;
    width: 500px;
    height: 320px;
    background-color: #ffffff;
    .img-box {
      width: 100%;
      height: 100px;
      display: flex;
      justify-content: center;
    }
    h2 {
      text-align: center;
    }
  }
  .n-form {
    // height: 200px;
    display: flex;
    flex-direction: column;
    align-items: center;
    .n-input {
      margin-top: 20px;
    }
  }
  .btn-box {
    margin-top: 20px;
    width: 100%;
    display: flex;
    justify-content: space-around;
  }
}
.auto-spaced-linear-gradient {
  background: linear-gradient(to right, #0f0c29, #302b63, #24243e);
}
</style>