<template>
    <div class="outer">
      <div class="header">
      <div class="dev"></div>
      <span>成绩占比</span>
    </div>
    <i>* </i><span>平时成绩占比：</span>
    <input-ref :maxValue="1" type="number" placeholder="请输入0-1以内数字">
    </input-ref>&nbsp;%<br>
    <i>* </i><span>期末成绩占比：</span>
    <input-ref :maxValue="1" type="number" placeholder="请输入0-1以内数字">
    </input-ref>&nbsp;%
  <add-examway></add-examway>
  <div>
    <el-button type="primary" @click="addEaxmWay">增加考核方式</el-button>
    <el-button @click="back">返回</el-button>
    <el-button type="primary" @click="save">保存</el-button>
  </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import AddExamway from '@/components/aboutCourse/AddExamway.vue'
import InputRef from '@/components/InputRef.vue'
export default defineComponent({
  name: 'ExamWay',
  components: {
    AddExamway,
    InputRef
  },
  setup () {
    const addEaxmWay = () => {
      console.log(111)
    }
    const back = () => {
      console.log(222)
    }
    const save = () => {
      console.log(333)
    }
    return {
      addEaxmWay,
      back,
      save
    }
  }
})
</script>

<style scoped>
.header {
  position: relative;
}
.header span {
  margin-left: 8px;
}
i {
  color: #ed6355;
}
.dev {
  width: 4px;
  height: 25px;
  display: inline-block;
  background-color: #337ef7;
  position: absolute;
  top: 22px;
}
</style>
