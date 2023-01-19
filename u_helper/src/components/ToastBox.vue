<template>
  <el-dialog
    v-model="dialogVisible"
    title="Tips"
    width="30%"
    :before-close="handleClose"
  >
    <span>{{ message }}</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogClose">取消</el-button>
        <el-button type="primary" @click="dialogConfirm">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts">
import mitt from 'mitt'
import { ref, onUnmounted, defineComponent } from 'vue'
import { ElMessageBox } from 'element-plus'
import router from '@/router'
type noReturn = () => void
type Events = {
  'on-dialog-open': noReturn,
  'on-dialog-confirm': noReturn,
  'on-dialog-unsave': noReturn
}
export const emitter = mitt<Events>()
export default defineComponent({
  props: {
    message: {
      type: String
    }
  },
  name: 'ToastBox'
})
</script>

<script lang="ts" setup>
const dialogVisible = ref(false)
const isSave = ref(false)
const dialogClose = () => {
  dialogVisible.value = false
}
const dialogOpen = () => {
  dialogVisible.value = true
}
const dialogConfirm = () => {
  console.log(isSave.value)
  dialogClose()
  if (isSave.value) {
    emitter.emit('on-dialog-confirm', () => null)
  } else {
    router.push('/')
  }
}
const handleClose = (done: () => void) => {
  ElMessageBox.confirm('确认关闭当前弹窗？将不会保留您所做的更改！')
    .then(() => {
      done()
    })
    .catch(() => {
      // catch error
    })
}
emitter.on('on-dialog-open', () => {
  isSave.value = true
  dialogOpen()
})
emitter.on('on-dialog-unsave', () => {
  isSave.value = false
  dialogOpen()
})
onUnmounted(() => {
  emitter.off('on-dialog-open')
})
</script>
<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
