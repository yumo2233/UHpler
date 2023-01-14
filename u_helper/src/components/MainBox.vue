<template>
  <div class="mainBox">
    <state-bar></state-bar>
    <router-view></router-view>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
// 组件
import StateBar from './StateBar.vue'
export default defineComponent({
  name: 'MainBox',
  components: {
    StateBar
  }
})
</script>

<style>
.mainBox {
  width: 100%;
  height: 100%;
}
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
  margin-bottom: 20px;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

</style>
