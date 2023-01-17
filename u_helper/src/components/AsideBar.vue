<template>
  <el-row class="tac">
    <el-col>
      <el-menu default-active="1-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
        <el-sub-menu index="1">
          <template #title>
            <el-icon><User /></el-icon>
            <span>智能课程</span>
          </template>
          <el-menu-item-group>
            <router-link to="/">
              <el-menu-item index="1-1"><el-icon><Avatar /></el-icon>课程管理</el-menu-item>
            </router-link>
            <router-link to="/class">
              <el-menu-item index="1-2"><el-icon><Avatar /></el-icon>班级管理</el-menu-item>
            </router-link>
            <el-menu-item index="1-3"><el-icon><Avatar /></el-icon>毕业要求管理</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
export default defineComponent({
  name: 'AsideBar',
  components: {
  }
})
</script>
<script lang="ts" setup>
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
</script>

<style scoped>
.tac {
  user-select: none;
}
</style>
