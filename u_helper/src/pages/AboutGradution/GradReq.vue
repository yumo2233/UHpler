<template>
  <div class="outer">
    <i class="red">* </i><span> 毕业要求{{ index }}</span>&nbsp;
    <el-input :disabled="isAuthor" @input="isChanged=true" v-model="name" class="w-50 m-2" style="height: 34px;width: 343px;"/>&nbsp;
    <el-button type="info" @click="deleteCur">删除毕业要求</el-button>&nbsp;
    <el-button type="primary" @click="addOne">增加指标点</el-button>
    <template v-for="(o, oindex) in info?.graduateTargetInfo" :key="o">
      <check-point :is-author="isAuthor" :info="o" :targetId="info?.graduateId" :index="oindex+1"></check-point>
    </template>
  </div>
</template>

<script lang="ts">
import { defineComponent, PropType, ref } from 'vue'
import { useRoute } from 'vue-router'
import { graduateName } from '@/store'
import CheckPoint from './CheckPoint.vue'
import { cemitter } from './CheckOrEditFGrad.vue'
import axios from 'axios'
import { apis } from '@/common/apis'
export default defineComponent({
  props: {
    index: Number,
    isAuthor: {
      type: Boolean,
      default: false
    },
    info: Object as PropType<graduateName>
  },
  components: {
    CheckPoint
  },
  name: 'GradReq',
  setup (props) {
    const route = useRoute()
    const deleteCur = () => {
      const id = props.info?.graduateId
      if (confirm('是否删除该毕业要求？') && !props.info?.add) {
        axios.get(`${apis.deleteGraduateInfo}/${id}`)
      }
      cemitter.emit('reload', () => null)
    }
    const name = ref(props.info?.graduateName)
    const isChanged = ref(false)
    cemitter.on('on-save', () => {
      if (isChanged.value) {
        if (!props.info?.add) {
          const obj = {
            id: props.info?.graduateId,
            name: name.value
          }
          axios.post(apis.updateGraduationName, obj)
        } else {
          const obj = {
            id: route.query.id,
            name: name.value
          }
          axios.post(apis.insertInfoTarget, obj)
        }
        cemitter.emit('reload', () => null)
      }
    })
    const addOne = () => {
      const obj = {
        name: '',
        content: ''
      }
      // eslint-disable-next-line vue/no-mutating-props
      props.info?.graduateTargetInfo.push(obj)
    }
    return {
      deleteCur, name, addOne, isChanged
    }
  }
})
</script>

<style scoped>
.outer {
  margin-top: 30px;
  padding: 20px;
  background-color: #f5f6f7;
}
</style>
