<template>
  <div class="outer">
    <div class="top">
      <div class="dev"></div>
      <span>考核方式1：<i @click="removeThisWay">（点击删除考核方式）</i></span>
    </div><br>
    <div>
      <i>* </i><span>考核方式名称：</span><input-ref class="right" placeholder="请输入20字以内字符" :maxLength="20"></input-ref>
    </div>
    <div>
      <i>* </i><span>占所有考核方式比重：</span><input-ref class="right" placeholder="请输入0-1以内数字" type="number"></input-ref>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
import InputRef from '../InputRef.vue'
export default defineComponent({
  name: 'AddExamway',
  components: {
    InputRef
  },
  setup () {
    const examName = ref('')
    const removeThisWay = () => {
      console.log(1)
    }
    return {
      examName,
      removeThisWay
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
}
.right {
  margin-top: 18px;
  position: absolute;
  left: 175px;
}
</style>
