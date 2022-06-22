<template>
  <!-- <div class="box"> -->
  <n-popconfirm positive-text="确认" negative-text="取消">
    <template #icon> </template>
    <template #trigger>
      <n-button size="small">删除</n-button>
    </template>
    确认删除吗
  </n-popconfirm>
  <!-- </div> -->
</template>

<script>
import { defineComponent } from "vue";
// import { MdHand as HandIcon } from "@vicons/ionicons4";

export default defineComponent({
  components: {
    // HandIcon
  }
});
</script>
<style lang="less" scoped>
// .box {
//   background-color: #000;
// }
</style>