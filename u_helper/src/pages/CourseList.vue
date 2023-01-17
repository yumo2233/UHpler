<template>
  <el-row :gutter="20" justify="space-between">
    <router-link to="/course">
      <el-col :span="6">
        <course-box :is-first="true"></course-box>
      </el-col>
    </router-link>
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
    return {
    }
  }
})
</script>
