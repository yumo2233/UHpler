<template>
  <el-row class="tac">
    <el-col>
      <h5 class="mb-2">主页</h5>
      <el-menu default-active="1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
        <el-sub-menu index="1">
          <template #title>
            <el-icon><User /></el-icon>
            <span>智能课程</span>
          </template>
          <el-menu-item-group>
            <router-link to="/course">
              <el-menu-item class="item" index="1-1"><el-icon><Avatar /></el-icon>课程管理</el-menu-item>
            </router-link>
            <router-link to="/class">
              <el-menu-item class="item" index="1-2"><el-icon><Avatar /></el-icon>班级管理</el-menu-item>
            </router-link>
            <el-menu-item class="item" index="1-3"><el-icon><Avatar /></el-icon>毕业要求管理</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
        <el-sub-menu index="2">
          <template #title>
            <el-icon><Calendar /></el-icon>
            <span>考勤管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item class="item" index="2-1" @click="attendanceDate"><el-icon><Finished /></el-icon>考勤日期</el-menu-item>
            <el-menu-item class="item" index="2-2" @click="attendanceManage"><el-icon><Checked /></el-icon>出勤管理</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
        <el-sub-menu index="3">
          <template #title>
            <el-icon><Tickets /></el-icon>
            <span>审批管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item class="item" index="3-1" @click="leaveApproval"><el-icon><CircleCheck /></el-icon>请假审批</el-menu-item>
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
const attendanceDate = () => {
  console.log(555)
}
const attendanceManage = () => {
  console.log(666)
}
const leaveApproval = () => {
  console.log(777)
}
</script>

<style>
.item:focus {
  background-color: rgba(51, 127, 231, .2);
  border-radius: 5px;
  font-weight: bolder;
  color: black;
}
</style>
