<template>
  <filter-bar></filter-bar>
  <el-table :data="classList" style="width: 100%;">
    <el-table-column prop="date" label="学院" width="180" />
    <el-table-column prop="name" label="年级" width="180" />
    <el-table-column prop="address" label="专业" />
    <el-table-column prop="address" label="班级" />
    <el-table-column prop="num1" label="人数" width="80" />
    <el-table-column prop="num2" label="进行中课程" />
    <el-table-column prop="address" label="操作">
    <div class="flex justify-space-between mb-4 flex-wrap gap-4">
      <el-button link type="primary" @click="goEdit">编辑</el-button>
      <el-button link type="primary" @click="goView">查看</el-button>
      <el-button link type="danger" @click="goDelete">删除</el-button>
    </div>
    </el-table-column>
  </el-table>
</template>

<script lang="ts">
import { defineComponent, onBeforeMount, computed } from 'vue'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store/index'
import FilterBar from '../components/FilterBar.vue'
export default defineComponent({
  name: 'ClassManage',
  components: {
    FilterBar
  },
  setup () {
    const store = useStore<GlobalDataProps>()
    const classList = computed(() => store.getters.updateVal({ grade: 0, profess: '' }))
    onBeforeMount(() => {
      store.dispatch('collegeAndGrade')
    })
    const goEdit = () => {
      console.log('edit')
    }
    const goView = () => {
      console.log('view')
    }
    const goDelete = () => {
      console.log('delete')
    }
    return {
      goEdit, goView, goDelete, classList
    }
  }
})
</script>
