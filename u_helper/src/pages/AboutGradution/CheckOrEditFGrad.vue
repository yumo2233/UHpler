<template>
  <div class="outer">
    <div class="upper">
      <h5>编辑/查看毕业要求管理</h5>
      <i class="red">* </i>学院： <el-input v-model="info.college" disabled class="w-50 m-2" style="height: 34px;width: 343px;margin-bottom: 20px;"/><br>
      <i class="red">* </i>专业： <el-input v-model="info.professional" disabled class="w-50 m-2" style="height: 34px;width: 343px;margin-bottom: 20px;"/><br>
      <i class="red">* </i>年级： <el-input v-model="info.grade" disabled class="w-50 m-2" style="height: 34px;width: 343px;"/><br>
    </div>
    <div class="gradreq">
      <template v-for="(o, index) in info.graduateName" :key="index">
        <grad-req :index="index+1" :info="o" :is-author="isAuthor"></grad-req>
      </template>
    </div>
    <div class="footer">
      <el-button type="primary" @click="addOne">增加毕业要求</el-button>
      <el-button type="primary" @click="goBack">返回</el-button>
      <el-button type="primary" @click="saveCurr">保存</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onBeforeMount, computed } from 'vue'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
import { useRouter, useRoute } from 'vue-router'
import mitt from 'mitt'
import GradReq from './GradReq.vue'
type Event = {
  reload: () => null,
  'on-save': () => null
}
export const cemitter = mitt<Event>()
export default defineComponent({
  props: {
    isAuthor: {
      type: Boolean,
      default: false
    }
  },
  components: {
    GradReq
  },
  name: 'CheckOrEdit',
  setup () {
    const router = useRouter()
    const route = useRoute()
    const store = useStore<GlobalDataProps>()
    const info = computed(() => store.state.gradInfo)
    const id = route.query.id
    onBeforeMount(() => {
      store.dispatch('getOneGradInfo', id)
    })
    const goBack = () => {
      router.push('/editgrad')
    }
    const addOne = () => {
      const obj = {
        graduateName: '',
        graduateTargetInfo: [],
        graduateId: Date.now(),
        add: true
      }
      info.value.graduateName.push(obj)
    }
    cemitter.on('reload', () => {
      store.dispatch('getOneGradInfo', id)
    })
    const saveCurr = () => {
      cemitter.emit('on-save', () => null)
    }
    return {
      goBack, info, addOne, saveCurr
    }
  }
})
</script>

<style scoped>
.footer {
  width: 260px;
  margin: 20px auto;
}
</style>
