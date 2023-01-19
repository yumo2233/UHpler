<template>
  <div class="outer">
    <div class="top">
      <div class="dev"></div>
      <span>考核方式{{ index }}：<i @click="removeThisWay">（点击删除考核方式）</i></span>
    </div><br>
    <div>
      <i>* </i><span>考核方式名称：</span>
      <span class="right">
        <el-input v-model="examName" class="w-50 m-2" maxlength="20" placeholder="请输入20字以内文字" style="height: 38px;width: 343px;"/>
      </span>
    </div>
    <div>
      <i>* </i><span>占所有考核方式比重：</span>
      <span class="right"><el-input type="number" v-model="Ratio" class="w-50 m-2" placeholder="请输入0-1以内数字" style="height: 38px;width: 343px;"/>&nbsp;%</span>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
export default defineComponent({
  props: {
    index: Number,
    info: Object
  },
  name: 'AddExamway',
  setup () {
    const examName = ref('')
    const Ratio = ref(0)
    const removeThisWay = () => {
      console.log(1)
    }
    return {
      examName,
      removeThisWay,
      Ratio
    }
  }
})
</script>

<style scoped>
.outer {
  position: relative;
  height: 100%;
  margin-top: 20px;
  user-select: none;
}
.top {
  position: relative;
}
.top span {
  position: absolute;
  top: 10px;
  left: 6px;
}
.top span i {
  color: grey;
}
.dev {
  width: 4px;
  height: 25px;
  display: inline-block;
  background-color: #337ef7;
  position: absolute;
  top: 32px;
}
i {
  color: #ed6355;
  font-style: normal;
}
.right {
  position: absolute;
  left: 175px;
}
</style>
