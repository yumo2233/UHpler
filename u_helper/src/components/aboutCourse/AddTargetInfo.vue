<template>
  <div class="outer">
    <div class="top">
      <div class="dev"></div>
      <span>课程目标{{ index }}<i @click="removeThisWay">（点击删除课程目标）</i></span>
    </div><br>
    <span>
        <i>* </i><span>课程目标名称：</span><el-input v-model="examName" maxlength="20" class="w-50 m-2 right" placeholder="请输入20字以内文字" style="height: 38px;width: 343px;"/>
    </span> <br>
    <span>
      <i>* </i><span>课程目标编码：</span><el-input v-model="id" maxlength="20" class="w-50 m-2 right" placeholder="请输入20字以内文字" style="height: 38px;width: 343px;"/>
    </span> <br>
    <span>
      <i>* </i><span>课程目标内容：</span><el-input  v-model="target" maxlength="20" class="w-50 m-2 right" placeholder="请输入20字以内文字" style="height: 38px;width: 343px;"/>
    </span> <br>
    <span>
      <i>* </i><span>关联毕业要求：</span>
      <span class="right">
        <el-cascader v-model="value" :options="options" :show-all-levels="false" style="width: 343px;bottom: 16px;" clearable/>
      </span>
    </span>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, computed, watch } from 'vue'
import mitt from 'mitt'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
type Remove = {
  'on-target-remove': number,
  'on-target-change': {
    index: number,
    value: number
  }
}
export const emitter = mitt<Remove>()
export default defineComponent({
  props: {
    info: Object,
    index: Number
  },
  name: 'AddTargetInfo',
  setup (props) {
    const store = useStore<GlobalDataProps>()
    const examName = ref(props.info?.name || '')
    const id = ref(props.info?.number || '')
    const target = ref(props.info?.content || '')
    const info = computed(() => store.state.currentCourse)
    const removeThisWay = () => {
      emitter.emit('on-target-remove', props.info?.id)
    }
    const value = ref(props.info?.graduateId || '')
    const options = computed(() => store.state.graduationList)
    watch([examName, id, target, value], () => {
      const index = props.index || -1
      if (index > 0) {
        info.value.targetList[index - 1].name = examName.value
        info.value.targetList[index - 1].number = id.value
        info.value.targetList[index - 1].content = target.value
        info.value.targetList[index - 1].graduateId = value.value[1]
      }
    })
    // const updateName = () => {
    //   if (index > 0) {
    //     store.state.currentCourse.targetList[index - 1].name = examName.value
    //   } else {
    //     console.log('保存新增考核方式名称失败')
    //   }
    // }
    // const updateId = () => {
    //   if (index > 0) {
    //     store.state.currentCourse.targetList[index - 1].number = id.value
    //   } else {
    //     console.log('保存新增考核方式名称失败')
    //   }
    // }
    // const updateContent = () => {
    //   if (index > 0) {
    //     store.state.currentCourse.targetList[index - 1].content = target.value
    //   } else {
    //     console.log('保存新增考核方式名称失败')
    //   }
    // }
    // const changeValue = () => {
    //   if (index > 0) {
    //     store.state.currentCourse.targetList[index - 1].graduateId = value.value
    //   } else {
    //     console.log('保存新增考核方式名称失败')
    //   }
    // }
    return {
      examName,
      removeThisWay,
      options,
      value,
      id,
      target
      // updateName,
      // updateId,
      // updateContent,
      // changeValue
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
  margin-top: 16px;
  position: absolute;
  left: 152px;
}
</style>
