<template>
  <el-drawer v-model="drawer" size="50%" @close="isEdit=false">
    <template #header>
      <h4>平时成绩管理</h4>
    </template>
    <template #default>
      <template v-if="isEdit">
        <h3>{{ rowInfo.name }}</h3>
        <template v-for="(o, index) in checkList" :key="o.id">
          <span>{{ o.name }}</span>
          <el-input-number v-model="rowInfo.usualScore[index]" :min="0" :max="100"/> <br>
        </template>
      </template>
      <template v-else>
        <choose-list></choose-list>
      </template>
    </template>
    <template #footer>
      <div style="flex: auto">
        <el-button @click="cancelClick">取消</el-button>
        <el-button type="primary" @click="confirmClick">保存</el-button>
      </div>
    </template>
  </el-drawer>
</template>

<script lang="ts">
import { ref, defineComponent, computed } from 'vue'
import mitt from 'mitt'
import { ElMessageBox } from 'element-plus'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
import ChooseList from './ChooseList.vue'
import axios from 'axios'
import { apis } from '@/common/apis'
type NoReturn = () => void
type Event = {
  'on-drawer-open': NoReturn,
  'on-drawer-close': NoReturn,
  'on-update-usual-grade': object
}
export const demitter = mitt<Event>()
export default defineComponent({
  name: 'DrawerVue',
  components: {
    ChooseList
  }
})
</script>

<script lang="ts" setup>
const store = useStore<GlobalDataProps>()
const drawer = ref(false)
const isEdit = ref(false)
const rowInfo = ref()
const info = computed(() => store.state.currentCourse)
const checkList = computed(() => store.state.currentCourse.checkList)
const stu = computed(() => store.state.stuGrade)
function cancelClick () {
  drawer.value = false
}
function confirmClick () {
  ElMessageBox.confirm('确认保存当前信息？')
    .then(() => {
      axios.post(apis.modefyChecklist, JSON.stringify({ courseId: info.value.id, targetAndCheckFormList: info.value.targetList })).then(res => {
        // if (res.data.success) {
        alert('保存成功')
        // }
      })
    })
    .catch(() => {
      // catch error
    })
}
demitter.on('on-drawer-open', () => {
  drawer.value = true
})
demitter.on('on-update-usual-grade', (row: object) => {
  isEdit.value = true
  rowInfo.value = row
  const len = checkList.value.length
  const currentStu = stu.value.find(item => item.id === rowInfo.value.id)
  const slen = currentStu?.usualScore.length || 0
  const dif = len - slen
  if (dif > 0) {
    for (let i = 0; i < dif; i++) {
      currentStu?.usualScore.push(0)
    }
  }
})
</script>
