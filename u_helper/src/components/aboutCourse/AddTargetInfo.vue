<template>
  <div class="outer">
    <div class="top">
      <div class="dev"></div>
      <span>课程目标1<i @click="removeThisWay">（点击删除考核方式）</i></span>
    </div><br>
    <div>
      <i>* </i><span>课程目标名称：</span><input-ref class="right" placeholder="课程目标" :maxLength="20"></input-ref>
    </div>
    <div>
      <i>* </i><span>课程目标编码：</span><input-ref class="right" placeholder="CT1" type="number"></input-ref>
    </div>
    <div>
      <i>* </i><span>课程目标内容：</span><input-ref class="right" :maxLength="200" placeholder="请输入200字以内课程目标内容" type="number"></input-ref>
    </div>
    <div>
      <i>* </i><span>关联毕业要求：</span>
      <el-select v-model="value" class="m-2 right" placeholder="请选择" style="width: 363px;">
      <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value"
    /></el-select>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
import InputRef from '../InputRef.vue'
export default defineComponent({
  name: 'AddTargetInfo',
  components: {
    InputRef
  },
  setup () {
    const examName = ref('')
    const removeThisWay = () => {
      console.log(1)
    }
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
    return {
      examName,
      removeThisWay,
      options,
      value
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
  left: 200px;
}
</style>
