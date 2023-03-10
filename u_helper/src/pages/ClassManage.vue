<template>
  <filter-bar @update="updateVal"></filter-bar>
  <el-table :data="classList" style="width: 100%;">
    <el-table-column prop="college" label="学院" width="180" />
    <el-table-column prop="grade" label="年级" width="180" />
    <el-table-column prop="professional" label="专业" />
    <el-table-column prop="name" label="班级" />
    <el-table-column prop="headcount" label="人数" width="80" />
    <el-table-column prop="courseCount" label="进行中课程" />
    <el-table-column label="操作">
      <template #default="scope">
        <div class="flex justify-space-between mb-4 flex-wrap gap-4">
          <el-button link type="primary" :disabled="classList[scope.$index].userId !== +userId" @click.prevent="goEdit(classList[scope.$index].id)">编辑</el-button>
          <el-button link type="primary" @click.prevent="goView(classList[scope.$index].id)">查看</el-button>
          <el-button link type="danger" :disabled="classList[scope.$index].userId !== +userId" @click.prevent="goDelete(classList[scope.$index].id)">删除</el-button>
        </div>
      </template>
    </el-table-column>
  </el-table>
</template>

<script lang="ts">
import { defineComponent, onBeforeMount, computed } from 'vue'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store/index'
import { useRouter } from 'vue-router'
import FilterBar from '../components/FilterBar.vue'
import axios from 'axios'
import { apis } from '@/common/apis'
export default defineComponent({
  name: 'ClassManage',
  components: {
    FilterBar
  },
  setup () {
    const router = useRouter()
    const store = useStore<GlobalDataProps>()
    const classList = computed(() => store.state.classfilter)
    const userId = computed(() => store.state.user.userId)
    onBeforeMount(() => {
      store.dispatch('selectClass')
    })
    const updateVal = (obj: { grade: number, profess: string }) => {
      store.getters.classfilter(obj)
    }
    const goEdit = (id: number) => {
      router.push({
        path: '/editclass',
        query: {
          id
        }
      })
    }
    const goView = (id: number) => {
      router.push({
        path: '/editclass',
        query: {
          id,
          view: 1
        }
      })
    }
    const goDelete = (id: number) => {
      if (confirm('确认删除？')) {
        axios.get(`${apis.deleteClassClass}/${id}`)
      }
      store.dispatch('selectClass')
    }
    return {
      goEdit, goView, goDelete, classList, updateVal, userId
    }
  }
})
</script>
