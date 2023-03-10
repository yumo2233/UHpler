<template>
  <div class="outer">
    <el-dialog v-model="centerDialogVisible" title="Warning" width="30%" center>
    <span>
      {{ message }}
    </span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSave6">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
    <template v-for="(o, index) in info.targetList" :key="o.id">
      <add-target-info :index="index+1" :info="o"></add-target-info>
    </template>
    <div>
      <el-button type="primary" @click="addTargetWay">增加课程目标</el-button>
      <el-button @click="handleBack">返回</el-button>
      <el-button type="primary" @click="handleSave5">保存</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onBeforeMount, computed } from 'vue'
import axios from 'axios'
import { apis } from '@/common/apis'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { GlobalDataProps } from '@/store'
import AddTargetInfo, { emitter } from '@/components/aboutCourse/AddTargetInfo.vue'
export default defineComponent({
  name: 'TargetInfo',
  components: {
    AddTargetInfo
  },
  setup () {
    const store = useStore<GlobalDataProps>()
    const router = useRouter()
    const info = computed(() => store.state.currentCourse)
    const message = ref('')
    const isAdd = computed(() => store.state.isAdd)
    const centerDialogVisible = ref(false)
    const backOrSave = ref(0)
    const addTargetWay = () => {
      info.value.targetList.push({
        id: Date.now(),
        courseId: Date.now(),
        content: '',
        number: '',
        name: '',
        graduateId: Date.now()
      })
    }
    const handleBack = () => {
      centerDialogVisible.value = true
      message.value = '确认关闭当前弹窗？将不会保留您所做的更改！'
    }
    const handleSave5 = () => {
      backOrSave.value = 1
      centerDialogVisible.value = true
      message.value = '保存后将覆盖已有信息，请确认输入信息正确无误'
    }
    const handleSave6 = () => {
      if (backOrSave.value) {
        backOrSave.value = 0
        if (!isAdd.value) {
          axios.post(apis.modfiy, JSON.stringify(info.value)).then(res => {
            if (res.status !== 200) {
              alert('保存失败')
            }
          })
        } else {
          axios.post(apis.addone, JSON.stringify(info.value)).then(res => {
            if (res.status !== 200) {
              alert('添加失败')
            }
          })
          router.push('/')
          store.commit('noAddOne')
        }
      }
      router.push('/')
    }
    emitter.on('on-target-remove', (id: number) => {
      const len = info.value.targetList.length
      if (len < 2) {
        alert('请至少保留一个课程目标信息！')
      } else {
        store.commit('removeThisWay', id)
      }
    })
    emitter.on('on-target-change', ({ index, value }) => {
      // console.log(index)
      if (index > -1) {
        info.value.targetList[index].graduateId = value
      } else {
        alert('something goes wrong')
      }
    })
    onBeforeMount(() => {
      store.dispatch('addGraduationList')
    })
    return {
      centerDialogVisible,
      addTargetWay,
      handleBack,
      handleSave5,
      handleSave6,
      message,
      info
    }
  }
})
</script>

<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
