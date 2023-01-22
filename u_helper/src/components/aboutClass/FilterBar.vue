<template>
  <div class="outer">
    <div class="academy">
      <span>学院/专业：</span>
      <el-select v-model="value" class="m-2" placeholder="Select">
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </div>
    <div class="grade">
      <span>年级：</span>
      <el-select v-model="value" class="m-2" placeholder="Select">
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </div>
    <div class="button">
      <el-button @click="search" type="primary">搜索</el-button>
      <el-button @click="addNew" type="primary">新增</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
export default defineComponent({
  name: 'FilterBar',
  setup () {
    const value = ref('')
    const options = [
      {
        value: 'Option1',
        label: 'Option1'
      },
      {
        value: 'Option2',
        label: 'Option2'
      }]
    const addNew = () => {
      console.log(111)
    }
    const search = () => {
      console.log(222)
    }
    return {
      value, options, addNew, search
    }
  }
})
</script>

<style scoped>
.outer {
  width: 100%;
  background-color: #fff;
  border-radius: 10px;
  height: 150px;
  user-select: none;
}
.academy, .grade {
  display: inline-block;
  margin-right: 100px;
  margin-left: 50px;
}
.button {
  margin-left: 50px;
}
</style>
