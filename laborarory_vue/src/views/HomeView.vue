<template>
  <n-space vertical>
    <n-layout>
      <n-layout-header bordered>
        <div class="title">实验室设备管理系统</div>
        <div class="info-box">
          <n-avatar
            round
            size="medium"
            src="https://07akioni.oss-cn-beijing.aliyuncs.com/07akioni.jpeg"
          />
          <span>admin</span>
          <span class="iconfont icon-message"></span>
          <n-popconfirm
            @positive-click="exit"
            @negative-click="handleNegativeClick"
          >
            <template #trigger>
              <span class="logout iconfont icon-zhuxiao"></span>
            </template>
            确认要退出系统吗？
          </n-popconfirm>
        </div>
      </n-layout-header>
      <n-layout has-sider>
        <n-layout-sider
          bordered
          show-trigger
          collapse-mode="width"
          :collapsed-width="64"
          :width="240"
          :native-scrollbar="false"
          style="min-height: 100vh"
        >
          <n-menu
            :collapsed-width="64"
            :collapsed-icon-size="22"
            :options="menuOptions"
          />
        </n-layout-sider>
        <n-layout />
        <n-message-provider>
          <n-dialog-provider>
            <router-view></router-view>
          </n-dialog-provider>
        </n-message-provider>
      </n-layout>
    </n-layout>
  </n-space>
</template>

<script>
import { storeToRefs } from 'pinia'
import { login } from '@/store/login'
import { useRouter } from 'vue-router'
import { h, defineComponent, ref } from "vue";
import { NIcon } from "naive-ui";
import { RouterLink } from "vue-router";
import {
  BookOutline as BookIcon,
  PersonOutline as PersonIcon,
  WineOutline as WineIcon,
  LaptopOutline as WorkIcon,
  LogOutOutline as LogoutIcon,
  HomeOutline as HomeIcon,

} from "@vicons/ionicons5";

function renderIcon(icon) {
  return () => h(NIcon, null, { default: () => h(icon) });
}


const menuOptions = [
  {
    label: () => h(RouterLink, {
      to: {
        path: "/home/index"
      }
    }, { default: () => "主页" }),
    key: "go-to-index",
    icon: renderIcon(HomeIcon)
  },
  {
    label: () => h(RouterLink, {
      to: {
        path: "/home/equipment"
      }
    }, { default: () => "设备管理" }),
    key: "go-to-equipment",
    icon: renderIcon(WorkIcon)
  },
  {
    label: () => h(RouterLink, {
      to: {
        path: "/home/repair"
      }
    }, { default: () => "修理记录" }),
    key: "go-to-repair",
    icon: renderIcon(BookIcon)
  },
  {
    label: () => h(RouterLink, {
      to: {
        path: "/home/scrap"
      }
    }, { default: () => "报废记录" }),
    key: "go-to-scrap",
    icon: renderIcon(WineIcon)
  },
  {
    label: () => h(RouterLink, {
      to: {
        path: "/home/apply"
      }
    }, { default: () => "购买申请" }),
    key: "go-to-apply",
    icon: renderIcon(PersonIcon)
  },
];

export default defineComponent({

  setup() {

    let loginStore = login()
    let { loginForm } = storeToRefs(loginStore)
    let router = useRouter()
    // console.log(loginForm.value);
    const exit = () => {
      window.sessionStorage.clear()
      loginForm.value.username = ''
      loginForm.value.password = ''
      router.push("/login")
    }
    return {
      inverted: ref(false),
      menuOptions,
      loginForm,
      exit
    };
  }
});
</script>

<style lang="less" scoped>
.n-memu {
  height: auto;
}
.n-layout-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.title {
  height: 50px;
  font-size: 16px;
  line-height: 50px;
  text-indent: 2em;
}
.info-box {
  width: 300px;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.img-box {
  width: 200px;
  height: 100%;
  background-color: #fff;
}
.n-layout {
  background-color: #1e1e1e;
}

.logout {
  cursor: pointer;
}
</style>