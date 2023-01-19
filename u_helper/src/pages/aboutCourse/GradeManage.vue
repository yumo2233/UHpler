<template>
  <toast-box></toast-box>
  <drawer-vue></drawer-vue>
  <div>
    <el-button>返回</el-button>
    <el-button type="primary" :disabled="isAuthor">下载</el-button>
    <el-button type="primary" @click="handleOpen" :disabled="isAuthor">平时成绩构成</el-button>
    <el-button :disabled="isAuthor" type="primary">保存</el-button>
  </div>
</template>

<script lang="ts">
import ToastBox from '@/components/ToastBox.vue'
import DrawerVue, { demitter } from '@/components/DrawerVue.vue'
import { defineComponent } from 'vue'
export default defineComponent({
  props: {
    isAuthor: {
      type: Boolean,
      default: true
    }
  },
  name: 'GradeManage',
  components: {
    ToastBox,
    DrawerVue
  },
  setup () {
    const handleOpen = () => {
      demitter.emit('on-drawer-open', () => null)
    }
    return {
      handleOpen
    }
  }
})
</script>
