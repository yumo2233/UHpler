<template>
  <el-tabs v-model="activeName" class="demo-tabs outer" @tab-click="handleClick">
    <el-tab-pane label="基础信息" name="first">
      <basic-info :isAuthor="false"></basic-info>
    </el-tab-pane>
    <el-tab-pane label="考核方式" name="second">
      <exam-way :isAuthor="false"></exam-way>
    </el-tab-pane>
    <el-tab-pane label="课程目标信息" name="third">
      <target-info :isAuthor="false"></target-info>
    </el-tab-pane>
    <el-tab-pane label="平时成绩管理" name="fourth">
      <grade-manage :isAuthor="false"></grade-manage>
    </el-tab-pane>
    <el-tab-pane label="期末成绩管理" name="fifth">期末成绩管理</el-tab-pane>
    <el-tab-pane label="课程报告(个人)" name="sixth">课程报告(个人)</el-tab-pane>
    <el-tab-pane label="课程报告(团队)" name="seventh">课程报告(团队)</el-tab-pane>
  </el-tabs>
</template>

<script lang="ts">
import type { TabsPaneContext } from 'element-plus'
import { defineComponent, ref } from 'vue'
import { useStore } from 'vuex'
import { useRoute } from 'vue-router'
import store, { GlobalDataProps } from '@/store'
import ExamWay from './aboutCourse/ExamWay.vue'
import TargetInfo from './aboutCourse/TargetInfo.vue'
import GradeManage from './aboutCourse/GradeManage.vue'
import BasicInfo from './aboutCourse/BasicInfo.vue'
export default defineComponent({
  name: 'CourseManage',
  components: {
    BasicInfo,
    ExamWay,
    TargetInfo,
    GradeManage
  }
})
</script>

<script lang="ts" setup>
const route = useRoute()
const activeName = ref('first')
// const isAuthor = ref(false)
const courseId = route.query.id
console.log(courseId)
if (courseId) {
  // store.dispatch('getCourse').then(res => { // 此处供修改信息时刷新页面所用
  const store = useStore<GlobalDataProps>()
  store.commit('addCurrentCourse', store.state.courses.find(item => item.id === +courseId))
  // })
  // isAuthor.value = courseInfo?.userId === store.state.user.userId
} else {
  store.commit('clearCurrentCourse')
}
const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log(tab, event)
}
</script>
<style scoped>
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
.outer {
  margin-top: 10px;
  height: 100%;
  background-color: white;
  border-radius: 10px;
  box-sizing: border-box;
  padding: 0 25px;
}
</style>
