<template>
  <div class="inner">
    <i class="red">* </i><span> 指标点{{ index }}</span>&nbsp;
    <el-input @input="isChanged=true" v-model="num" :disabled="isAuthor" class="w-50 m-2" style="height: 34px;width: 50px;"/>&nbsp;
    <el-input @input="isChanged=true" v-model="content" :disabled="isAuthor" class="w-50 m-2" style="height: 34px;width: 500px;"/>
    <!-- 此处绑定编号 -->
    <el-button :disabled="isAuthor" text @click="deleteCur">删除指标点</el-button>
    <br>
  </div>
</template>

<script lang="ts">
import { defineComponent, PropType, ref } from 'vue'
import { graduateTargetInfo } from '@/store/index'
import { apis } from '@/common/apis'
import axios from 'axios'
import { cemitter } from './CheckOrEditFGrad.vue'
export default defineComponent({
  name: 'CheckPoint',
  props: {
    index: Number,
    isAuthor: Number,
    info: Object as PropType<graduateTargetInfo>,
    targetId: Number
  },
  setup (props) {
    const isChanged = ref(false)
    const num = ref(props.info?.name)
    const content = ref(props.info?.content)
    const deleteCur = () => {
      const id = props.info?.id
      if (confirm('确认删除该指标点？') && id !== undefined) {
        axios.get(`${apis.deleteTarget}/${id}`)
      }
      cemitter.emit('reload', () => null)
    }
    cemitter.on('on-save', () => {
      if (isChanged.value) {
        if (props.info?.id) {
          const obj = {
            id: props.info.id,
            content: content.value,
            name: num.value
          }
          axios.post(apis.updateTarget, obj)
        } else {
          const obj = {
            id: props.targetId,
            content: content.value,
            name: num.value
          }
          axios.post(apis.insertTarget, obj)
        }
      }
    })
    return {
      isChanged, num, content, deleteCur
    }
  }
})
</script>

<style scoped>
.inner {
  margin-top: 30px;
  margin-left: 50px;
}
</style>
