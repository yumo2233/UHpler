<template>
  <filter-bar @update="updateVal"></filter-bar>
  <el-table :data="detailData" style="width: 100%">
    <el-table-column prop="college" label="学院"/>
    <el-table-column prop="grade" label="年级"/>
    <el-table-column prop="professional" label="专业"/>
    <el-table-column prop="graduateCount" label="毕业要求个数"/>
    <el-table-column prop="graduateTargetCount" label="指标点数"/>
    <el-table-column prop="address" label="操作">
      <template #default="scope">
          <div class="flex justify-space-between mb-4 flex-wrap gap-4">
          <el-button link type="primary" :disabled="isAuthor" @click.prevent="goEdit(detailData[scope.$index].id)">编辑</el-button>
          <el-button link type="primary" @click.prevent="goView">查看</el-button>
          <el-button link type="danger" :disabled="isAuthor" @click.prevent="goDelete">删除</el-button>
        </div>
      </template>
    </el-table-column>
  </el-table>
</template>

<script lang="ts">
import { defineComponent, onBeforeMount, computed, ref } from 'vue'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
import FilterBar from '@/components/FilterBar.vue'
import router from '@/router'
export default defineComponent({
  name: 'GradList',
  components: {
    FilterBar
  }
})
</script>

<script lang="ts" setup>
const store = useStore<GlobalDataProps>()
const detailData = computed(() => store.state.filterArray)
const goEdit = (id: number) => {
  router.push({
    path: '/editgrad',
    query: {
      id
    }
  })
}
const isAuthor = ref(false)
const goView = () => {
  console.log('view')
}
const goDelete = () => {
  console.log('delete')
}
const updateVal = (obj: { grade: number, profess: string }) => {
  store.getters.updateVal(obj)
}
onBeforeMount(() => {
  store.dispatch('collegeAndGrade')
  // here change isAuthor
})
</script>
