<template>
  <add-target-info></add-target-info>
  <div>
    <el-button type="primary" @click="addEaxmWay">增加课程目标</el-button>
    <el-button @click="back">返回</el-button>
    <el-button type="primary" @click="save">保存</el-button>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import AddTargetInfo from '@/components/aboutCourse/AddTargetInfo.vue'
export default defineComponent({
  name: 'TargetInfo',
  components: {
    AddTargetInfo
  },
  setup () {
    const addEaxmWay = () => {
      console.log(11)
    }
    const back = () => {
      console.log(22)
    }
    const save = () => {
      console.log(33)
    }
    return {
      addEaxmWay,
      back,
      save
    }
  }
})
</script>
