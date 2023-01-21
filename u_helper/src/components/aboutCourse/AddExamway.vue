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
import { defineComponent, ref, watch } from 'vue'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
import mitt from 'mitt'
type Delete = {
  'on-item-delete': number
}
export const Aemitter = mitt<Delete>()
export default defineComponent({
  props: {
    index: Number,
    info: Object
  },
  name: 'AddExamway',
  setup (props) {
    const store = useStore<GlobalDataProps>()
    const examName = ref(props.info?.name || '')
    const Ratio = ref(props.info?.ratio || 0)
    const removeThisWay = () => {
      Aemitter.emit('on-item-delete', props.info?.id)
    }
    watch([examName, Ratio], () => {
      const index = props.index || -1
      if (index > 0) {
        store.state.currentCourse.checkList[index - 1].name = examName.value
      } else {
        console.log('保存新增考核方式名称失败')
      }
    })
    // const updateName = () => {
    //   const index = props.index || -1
    //   if (index > 0) {
    //     store.state.currentCourse.checkList[index - 1].name = examName.value
    //   } else {
    //     console.log('保存新增考核方式名称失败')
    //   }
    // }
    // const updateRatio = () => {
    //   const index = props.index || -1
    //   if (index > 0) {
    //     store.state.currentCourse.checkList[index - 1].ratio = +Ratio.value
    //   } else {
    //     console.log('保存新增考核方式比重失败')
    //   }
    // }
    return {
      // updateRatio,
      // updateName,
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
