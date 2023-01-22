<template>
  <el-table :data="tableData" height="450" style="width: 100%;">
    <el-table-column prop="date" label="学院" width="180" />
    <el-table-column prop="name" label="年级" width="180" />
    <el-table-column prop="address" label="专业" />
    <el-table-column prop="address" label="班级" />
    <el-table-column prop="num1" label="人数" width="80" />
    <el-table-column prop="num2" label="进行中课程" />
    <el-table-column prop="address" label="操作">
    <function-button></function-button>
    </el-table-column>
  </el-table>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import FunctionButton from '@/components/aboutClass/FunctionButton.vue'
export default defineComponent({
  name: 'ClassDetail',
  components: {
    FunctionButton
  },
  setup () {
    const tableData = [
      {
        date: '2016-05-03',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
        num1: 100,
        num2: 5
      },
      {
        date: '2016-05-02',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
        num1: 100,
        num2: 5
      },
      {
        date: '2016-05-04',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
        num1: 100,
        num2: 5
      },
      {
        date: '2016-05-01',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
        num1: 100,
        num2: 5
      },
      {
        date: '2016-05-08',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
        num1: 100,
        num2: 5
      },
      {
        date: '2016-05-06',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
        num1: 100,
        num2: 5
      },
      {
        date: '2016-05-07',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
        num1: 100,
        num2: 5
      }
    ]
    return {
      tableData
    }
  }
})
</script>
