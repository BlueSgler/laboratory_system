<template>
  <n-config-provider :theme="darkTheme">
    <router-view></router-view>
  </n-config-provider>
</template>
<script>
import { defineComponent } from 'vue'
import { darkTheme } from 'naive-ui'
export default defineComponent({
  setup() {
    return {
      darkTheme
    }
  }
})
</script>
<style lang="less">
* {
  margin: 0;
  padding: 0;
}
#app {
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
    "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  color: #111;
  -webkit-font-smoothing: antialiased;
}
a {
  color: #111;
  text-decoration: none;
  cursor: pointer;
}
li {
  list-style: none;
}
img,
span,
a,
.el-icon {
  vertical-align: middle;
}
.iconfont {
  font-family: "iconfont" !important;
  font-size: 16px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
