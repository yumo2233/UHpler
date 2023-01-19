<template>
  <div class="outer">
    <div class="top">
      <div class="dev"></div>
      <span>课程目标1<i @click="removeThisWay">（点击删除课程目标）</i></span>
    </div><br>
    <div>
      <i>* </i><span>课程目标名称：</span><el-input v-model="examName" :disabled="isAuthor" maxlength="20" class="w-50 m-2 right" placeholder="请输入20字以内文字" style="height: 38px;width: 343px;"/>
    </div>
    <div>
      <i>* </i><span>课程目标编码：</span><el-input v-model="id" :disabled="isAuthor" maxlength="20" class="w-50 m-2 right" placeholder="请输入20字以内文字" style="height: 38px;width: 343px;"/>
    </div>
    <div>
      <i>* </i><span>课程目标内容：</span><el-input  v-model="target" :disabled="isAuthor" maxlength="20" class="w-50 m-2 right" placeholder="请输入20字以内文字" style="height: 38px;width: 343px;"/>
    </div>
    <div>
      <i>* </i><span>关联毕业要求：</span>
      <el-select v-model="value" :disabled="isAuthor" class="m-2 right" placeholder="请选择" size="large" style="width: 343px;">
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, PropType } from 'vue'
import { ICourses } from '@/store'
export default defineComponent({
  props: {
    isAuthor: {
      type: Boolean
    },
    info: {
      type: Object as PropType<ICourses>
    }
  },
  name: 'AddTargetInfo',
  setup () {
    const examName = ref('')
    const id = ref('')
    const target = ref('')
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
      value,
      id,
      target
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
  margin-top: 18px;
  position: absolute;
  left: 200px;
}
</style>
