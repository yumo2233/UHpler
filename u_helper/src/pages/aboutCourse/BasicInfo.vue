<template>
  <div class="outer">
    <span><i>*</i> 所属院系：</span>
    <span>
      <el-radio-group v-model="radio">
        <el-radio :label="3">通信与信息工程学院</el-radio>
        <el-radio :label="6">电子工程学院</el-radio>
        <el-radio :label="9">自动化学院</el-radio>
        <el-radio :label="12">计算机学院</el-radio>
      </el-radio-group>
    </span><br>
    <span><i>*</i> 开设单位：</span>
    <span class="right"><input-ref placeholder="请输入20字以内文字" type="string" v-model="academy"></input-ref></span><br>
    <span><i>*</i> 任课老师姓名：</span>
    <span class="right"><input-ref placeholder="请输入20字以内文字" type="string" v-model="teacherName"></input-ref></span><br>
    <span><i>*</i> 课程名称：</span>
    <span class="right"><input-ref placeholder="请输入20字以内文字" type="string" v-model="courseName"></input-ref></span><br>
    <span><i>*</i> 课程性质：</span>
      <span class="right">
        <el-radio-group v-model="radio1" class="ml-4">
          <el-radio :label="1" size="large">Option 1</el-radio>
          <el-radio :label="2" size="large">Option 2</el-radio>
        </el-radio-group>
      </span><br>
    <span><i>*</i> 课程编号：</span>
    <span class="right"><input-ref placeholder="请输入20字以内字符" type="string" v-model="courseId"></input-ref></span><br>
    <span><i>*</i> 总学时：</span>
    <span class="right"><input-ref placeholder="请输入0-100以内纯数字" type="number" v-model="totalTime"></input-ref></span><br>
      <span><i>*</i> 理论学时：</span>
    <span class="right"><input-ref placeholder="请输入0-100以内纯数字" type="number" v-model="theorTime"></input-ref></span><br>
    <span><i>*</i> 实验学时：</span>
    <span class="right"><input-ref placeholder="请输入0-100以内纯数字" type="number" v-model="labTime"></input-ref></span><br>
    <span><i>*</i> 学分：</span>
    <span class="right"><input-ref placeholder="请输入0-10以内纯数字" type="number" v-model="grade"></input-ref></span><br>
    <span><i>*</i> 先修课程：</span>
    <span class="right">
      <el-select v-model="courseKind" class="m-2" placeholder="请选择" size="large" style="width: 363px;">
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </span><br>
    <span><i>*</i> 考核对象：</span>
    <span class="right">
      <el-select v-model="courseKind" class="m-2" placeholder="请选择" size="large" style="width: 363px;">
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </span><br>
    <span><i>*</i> 学生人数：</span>
    <span class="right"><input-ref placeholder="请输入0-500以内纯数字" type="number" v-model="stuNum"></input-ref></span><br>
    <span><i>*</i> 执行学期：</span>
    <span class="right">
      <el-select v-model="courseKind" class="m-2" placeholder="请选择" size="large" style="width: 363px;">
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </span><br>
    <el-button type="primary" style="margin: 0 auto;">保存</el-button>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
import InputRef from '@/components/InputRef.vue'
export default defineComponent({
  name: 'BasicInfo',
  components: {
    InputRef
  },
  setup () {
    const radio = ref(3)
    const radio1 = ref(1)
    const academy = ref('')
    const teacherName = ref('')
    const courseName = ref('')
    const courseKind = ref('')
    const courseId = ref('')
    const totalTime = ref(0)
    const theorTime = ref(0)
    const labTime = ref(0)
    const grade = ref(0)
    const stuNum = ref(0)
    const options = [
      {
        value: 'Option1',
        label: 'Option1'
      },
      {
        value: 'Option2',
        label: 'Option2'
      }
    ]
    return {
      radio, radio1, academy, teacherName, courseName, courseKind, courseId, totalTime, theorTime, labTime, grade, stuNum, options
    }
  }
})
</script>

<style scoped>
.outer {
  width: 100%;
  position: relative;
}
i {
  color: #ed6355;
}
.right {
  position: absolute;
  left: 152px;
}
</style>
