<template>
  <el-row :gutter="20" justify="space-between">
    <el-col :span="6">
      <course-box :is-first="true" @click="createCourse"></course-box>
    </el-col>
    <el-col :span="6" v-for="o in 11" :key="o">
      <course-box></course-box>
    </el-col>
  </el-row>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import CourseBox from '@/components/CourseBox.vue'
export default defineComponent({
  name: 'CourseList',
  components: {
    CourseBox
  },
  setup () {
    const createCourse = () => {
      // 跳转路由
      console.log(111)
    }
    return {
      createCourse
    }
  }
})
</script>
