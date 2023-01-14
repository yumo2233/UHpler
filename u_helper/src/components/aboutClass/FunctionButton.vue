<template>
  <div class="flex justify-space-between mb-4 flex-wrap gap-4">
    <el-button link type="primary" @click.prevent="goEdit">编辑</el-button>
    <el-button link type="primary" @click.prevent="goView">查看</el-button>
    <el-button link type="danger" @click.prevent="goDelete">删除</el-button>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
export default defineComponent({
  name: 'FunctionButton',
  setup () {
    const goEdit = () => {
      console.log(11)
    }
    const goView = () => {
      console.log(22)
    }
    const goDelete = () => {
      console.log(33)
    }
    return {
      goEdit, goView, goDelete
    }
  }
})
</script>
