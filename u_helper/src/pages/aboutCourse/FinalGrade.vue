<template>
  <div class="outer">
    <up-load v-if="goUpload" @close-model="handleClose" :website="'https://mock.apifox.cn/m1/2206130-0-default/course/uploadFinalScore/'+info.id"></up-load>
    <edit-final-con></edit-final-con>
    <el-table :data="stu" border style="width: 100%">
      <el-table-column prop="className" label="年级" />
      <el-table-column prop="id" label="学号" />
      <el-table-column prop="name" label="姓名" />
      <el-table-column label="第一题">
        <template v-for="(o, index) in gradContent.first1" :key="o">
          <el-table-column :label="o[(index+1).toString()]">
            <el-table-column :label="index+1">
              <template #default="scope">
                {{ stu[scope.$index].finalScore1[index] }}
              </template>
            </el-table-column>
          </el-table-column>
        </template>
      </el-table-column>
      <el-table-column label="第二题">
        <template v-for="(o, index) in gradContent.first2" :key="o">
          <el-table-column :label="o[(index + 1).toString()]">
            <el-table-column :label="index + 1">
              <template #default="scope">
                {{ stu[scope.$index].finalScore2[index] }}
              </template>
            </el-table-column>
          </el-table-column>
        </template>
      </el-table-column>
      <el-table-column label="第三题">
        <template v-for="(o, index) in gradContent.first3" :key="o">
          <el-table-column :label="o[(index + 1).toString()]">
            <el-table-column :label="index + 1">
              <template #default="scope">
                {{ stu[scope.$index].finalScore3[index] }}
              </template>
            </el-table-column>
          </el-table-column>
        </template>
      </el-table-column>
      <el-table-column label="第四题">
        <template v-for="(o, index) in gradContent.first4" :key="o">
          <el-table-column :label="o[(index + 1).toString()]">
            <el-table-column :label="index + 1">
              <template #default="scope">
                {{ stu[scope.$index].finalScore1[index] }}
              </template>
            </el-table-column>
          </el-table-column>
        </template>
      </el-table-column>
    </el-table>
    <div class="button">
      <el-button @click="goBack">返回</el-button>
      <el-button @click="goedit" type="primary" :disabled="isAuthor">编辑题目</el-button>
      <el-button @click="godown" type="primary">下载</el-button>
      <el-button @click="downGrade" type="primary">下载成绩模板</el-button>
      <el-button @click="upload" type="primary">批量上传</el-button>
      <el-button @click="goBack" type="primary" :disabled="isAuthor">保存</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, computed, onBeforeMount } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
// import axios from 'axios'
// import { apis } from '@/common/apis'
import UpLoad from '@/components/UpLoad.vue'
import EditFinalCon, { emitter } from '@/components/aboutCourse/EditFinalCon.vue'

export default defineComponent({
  props: {
    isAuthor: {
      type: Boolean
    }
  },
  name: 'FinalGrade',
  components: {
    UpLoad, EditFinalCon
  }
})
</script>

<script lang="ts" setup>
const store = useStore<GlobalDataProps>()
const router = useRouter()
const goUpload = ref(false)
const gradContent = computed(() => store.state.gradContent)
const info = computed(() => store.state.currentCourse)
const stu = computed(() => store.state.stuGrade)
onBeforeMount(() => {
  store.dispatch('getGradContent', info.value.id)
})
const handleClose = () => {
  goUpload.value = false
  store.dispatch('getUsualGrade', info.value.id)
}
const goBack = () => {
  router.push('/')
}
// TODO
const downGrade = () => {
  window.open('')// 下载地址
}
const goedit = () => {
  emitter.emit('drawer-open', () => null)
}
const upload = () => {
  goUpload.value = true
  console.log(111)
}
// TODO
const godown = () => {
  // axios.get(`${apis.downloadFinal}/${info.value.id}`)
  window.open()
}
emitter.on('drawer-close', () => {
  store.dispatch('getGradContent', info.value.id)
})
</script>
