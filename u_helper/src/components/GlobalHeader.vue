<template>
  <el-menu class="el-menu-demo" mode="horizontal" :ellipsis="false">
    <el-menu-item index="0">U助教</el-menu-item>
    <div class="flex-grow" />
    <!-- <el-menu-item index="1"><el-icon><Bell /></el-icon>消息通知</el-menu-item> -->
    <el-menu-item index="2" @click="mailto"><el-icon><Headset /></el-icon>联系客服</el-menu-item>
    <el-menu-item index="3" @click="logout"><el-icon><Headset /></el-icon>退出登录</el-menu-item>
  </el-menu>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import store from '@/store'
export default defineComponent({
  name: 'GlobalHeader'
})
</script>

<script lang="ts" setup>
const logout = () => {
  store.commit('logout')
}
const mailto = () => {
  window.open('mailto:harlanlambert@gmail.com')
}
</script>

<style>
.flex-grow {
  flex-grow: 1;
}
</style>
