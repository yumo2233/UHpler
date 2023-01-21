<template>
  <el-row :gutter="20" justify="start">
    <router-link to="/course">
      <el-col :span="6">
        <course-box @click="addOne" :is-first="true"></course-box>
      </el-col>
    </router-link>
    <el-col :span="6" v-for="o in courseList" :key="o.id" class="autoMation">
      <router-link :to="{
        path: '/course',
        query: {
          id: o.id
        }
      }">
        <course-box :courses="o"></course-box>
      </router-link>
    </el-col>
  </el-row>
</template>

<script lang="ts">
import { defineComponent, onBeforeMount, computed } from 'vue'
import CourseBox from '@/components/CourseBox.vue'
import { GlobalDataProps } from '@/store/index'
import { useStore } from 'vuex'
export default defineComponent({
  name: 'CourseList',
  components: {
    CourseBox
  },
  setup () {
    const store = useStore<GlobalDataProps>()
    onBeforeMount(() => {
      store.dispatch('getCourse')
    })
    const courseList = computed(() => store.state.courses)
    const addOne = () => {
      store.commit('addOne')
    }
    return {
      courseList, addOne
    }
  }
})
</script>
