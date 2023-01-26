<template>
  <el-drawer v-model="drawer" size="50%">
    <template #header>
      <h4>平时成绩管理</h4>
    </template>
    <template #default>
      <div>
      </div>
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
import { ref, defineComponent } from 'vue'
import mitt from 'mitt'
import { ElMessageBox } from 'element-plus'
type NoReturn = () => void
type Event = {
  'on-drawer-open': NoReturn,
  'on-drawer-close': NoReturn,
  'on-update-usual-grade': object
}
export const demitter = mitt<Event>()
export default defineComponent({
  name: 'DrawerVue'
})
</script>

<script lang="ts" setup>
const drawer = ref(false)
function cancelClick () {
  drawer.value = false
}
function confirmClick () {
  ElMessageBox.confirm('确认保存当前信息？')
    .then(() => {
      drawer.value = false
    })
    .catch(() => {
      // catch error
    })
}
demitter.on('on-drawer-open', () => {
  drawer.value = true
})
demitter.on('on-update-usual-grade', (row: object) => {
  console.log(row)
})
</script>
