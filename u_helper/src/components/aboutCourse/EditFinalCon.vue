<template>
  <el-drawer title="期末成绩构成" v-model="dialog" size="40%">
    <div class="outer">
      <template v-for="(o) in targetList" :key="o.targetId">
        <span style="font-size: 14px;font-weight: bold;">{{ o.targetName }}</span> <br>
        <div class="first outerl">
          <span>第一题</span>
          <el-checkbox-group v-model="o.first1">
            <template v-for="i in 19" :key="i">
              <el-checkbox :label="i" />
            </template>
          </el-checkbox-group>
        </div>
        <div class="second outerl">
          <span>第二题</span>
          <el-checkbox-group v-model="o.first2">
            <template v-for="i in 19" :key="i">
              <el-checkbox :label="i" />
            </template>
          </el-checkbox-group>
        </div>
        <div class="third outerl">
          <span>第三题</span>
          <el-checkbox-group v-model="o.first3">
            <template v-for="i in 19" :key="i">
              <el-checkbox :label="i" />
            </template>
          </el-checkbox-group>
        </div>
        <div class="forth outerl">
          <span>第四题</span>
          <el-checkbox-group v-model="o.first4">
            <template v-for="i in 19" :key="i">
              <el-checkbox :label="i" />
            </template>
          </el-checkbox-group>
        </div>
      </template>
    </div>
    <div class="button">
      <el-button type="info" @click="dialog=false">取消</el-button>
      <el-button type="primary" @click="handleSave">保存</el-button>
    </div>
  </el-drawer>
</template>

<script lang="ts">
import { defineComponent, ref, computed, onBeforeMount } from 'vue'
import { apis } from '@/common/apis'
import mitt from 'mitt'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
import axios from 'axios'
type Event = {
  'drawer-open': () => null
  'drawer-close': () => null
}
export const emitter = mitt<Event>()
export default defineComponent({
  name: 'EditFinalCon',
  setup () {
    const store = useStore<GlobalDataProps>()
    const id = computed(() => store.state.currentCourse.id)
    const targetList = computed(() => store.state.targetAndFinalFormList)
    const dialog = ref(false)
    onBeforeMount(() => {
      // code
      store.dispatch('listFinalStructure', id.value)
    })
    const handleSave = () => {
      // code
      axios.post(apis.modifyFinalStructure, { ...targetList.value, courseId: id.value })
      emitter.emit('drawer-close', () => null)
    }
    // 事件
    emitter.on('drawer-open', () => {
      dialog.value = true
    })
    emitter.on('drawer-close', () => {
      dialog.value = false
    })
    return {
      targetList, dialog, handleSave
    }
  }
})
</script>

<style scoped>
span {
  font-size: 12px;
}

.outerl {
  outline: 1px solid black;
  margin-top: 5px;
}
</style>
