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
        <el-cascader v-model="checkObj" ref="casRef" :options="options" :props="customProps" :show-all-levels="false" style="width: 343px;bottom: 16px;" clearable/>
      </span>
    </span>
  </div>
</template>

<script lang="ts">
import { defineComponent, computed, Ref, ref } from 'vue'
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
    const targetList = computed(() => store.state.currentCourse.targetList)
    const casRef: Ref = ref(null)
    const examName = computed({
      get: () => targetList.value.find(item => item.id === props.info?.id)?.name,
      set: (value) => {
        store.commit('updateExName', { value, index: props.info?.id })
      }
    })
    const id = computed({
      get: () => targetList.value.find(item => item.id === props.info?.id)?.id,
      set: (value) => {
        store.commit('updateExId', { value, index: props.info?.id })
      }
    })
    const target = computed({
      get: () => targetList.value.find(item => item.id === props.info?.id)?.content,
      set: (value) => {
        store.commit('updateExTar', { value, index: props.info?.id })
      }
    })
    const checkObj = computed({
      get: () => targetList.value.find(item => item.id === props.info?.id)?.graduateId,
      set: (value) => {
        store.commit('updateExGra', { value, index: props.info?.id })
      }
    })
    const customProps = {
      multiple: true,
      emitPath: true
    }
    // const updateVal = () => {
    //   try {
    //     const value = (casRef.value?.getCheckedNodes(true) as []).map((ele: any) => ele.val)
    //     console.log(value)
    //   } catch (err) {
    //     alert('error')
    //   }
    // }
    // const info = computed(() => store.state.currentCourse)
    const removeThisWay = () => {
      emitter.emit('on-target-remove', props.info?.id)
    }
    // const value = ref(props.info?.graduateId || '')
    const options = computed(() => store.state.graduationList)
    return {
      examName,
      removeThisWay,
      options,
      id,
      target,
      casRef,
      checkObj,
      customProps
      // updateName,
      // updateId,
      // updateContent,
      // changeValue
    }
  }
})
console.log(11)
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
