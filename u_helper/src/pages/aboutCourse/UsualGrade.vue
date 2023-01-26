<template>
  <div class="outer">
    <el-dialog v-model="centerDialogVisible" title="Warning" width="30%" center>
      <span>
        {{ message }}
      </span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="centerDialogVisible = false">Cancel</el-button>
          <el-button type="primary" @click="centerDialogVisible = false">
            Confirm
          </el-button>
        </span>
      </template>
    </el-dialog>
    <div>
      <el-table :data="stu" style="width: 100%" @row-click="goEdit">
        <el-table-column label="班级" prop="className"/>
        <el-table-column label="学号" prop="number"/>
        <el-table-column label="姓名" prop="name"/>
        <template v-for="i in info.targetList" :key="i.id">
          <el-table-column :label="i.name">
            <template v-for="(j, index) in info.checkList" :key="j.id">
              <el-table-column :label="j.name">
                <template #default="scope">
                  <span>{{ stu[scope.$index].usualScore[index] }}</span>
                </template>
              </el-table-column>
            </template>
            <el-table-column label="总分"/>
            <el-table-column label="得分"/>
          </el-table-column>
        </template>
      </el-table>
    </div>
    <drawer-vue></drawer-vue>
    <div style="margin-bottom: 0;">
      <el-button type="primary" :disabled="isAuthor" @click="handleDownload">下载</el-button>
      <el-button type="primary" @click="handleOpen" :disabled="isAuthor">平时成绩构成</el-button>
      <el-button :disabled="isAuthor" type="primary" @click="centerDialogVisible = true">保存</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import DrawerVue, { demitter } from '@/components/DrawerVue.vue'
import { defineComponent, ref, computed, onBeforeMount } from 'vue'
import { GlobalDataProps } from '@/store'
import { useStore } from 'vuex'
console.log(7)
export default defineComponent({
  props: {
    isAuthor: {
      type: Boolean,
      default: true
    }
  },
  name: 'GradeManage',
  components: {
    DrawerVue
  },
  setup () {
    const store = useStore<GlobalDataProps>()
    const message = ref('')
    const centerDialogVisible = ref(false)
    const info = computed(() => store.state.currentCourse)
    const stu = computed(() => store.state.stuGrade)
    const handleOpen = () => {
      demitter.emit('on-drawer-open', () => null)
    }
    const handleDownload = () => {
      console.log('handle download')
    }
    onBeforeMount(() => {
      store.dispatch('getUsualGrade', info.value.id)
    })
    const grade = computed(() => store.state.stuGrade)
    const goEdit = (row: object) => {
      handleOpen()
      demitter.emit('on-update-usual-grade', row)
    }
    return {
      info,
      message,
      handleOpen,
      handleDownload,
      centerDialogVisible,
      grade,
      stu,
      goEdit
    }
  }
})
console.log(8)
</script>
