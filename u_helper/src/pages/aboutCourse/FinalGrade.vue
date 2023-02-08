<template>
  <div class="outer">
    <up-load v-if="goUpload" @close-model="goUpload = false"></up-load>
    <el-table :data="data" border style="width: 100%">
      <el-table-column prop="date" label="姓名"/>
      <el-table-column prop="name" label="第一题">
        <template v-for="(o) in gradContent" :key="o.targetId">
          <el-table-column :label="o.targetName">
            {{ o }}
          </el-table-column>
        </template>
      </el-table-column>
      <el-table-column prop="address" label="第二题">
        <template v-for="(o) in gradContent" :key="o.targetId">
          <el-table-column :label="o.targetName">
          </el-table-column>
        </template>
      </el-table-column>
      <el-table-column prop="address" label="第三题">
        <template v-for="(o) in gradContent" :key="o.targetId">
          <el-table-column :label="o.targetName">
          </el-table-column>
        </template>
      </el-table-column>
      <el-table-column prop="address" label="第四题">
        <template v-for="(o) in gradContent" :key="o.targetId">
          <el-table-column :label="o.targetName">
          </el-table-column>
        </template>
      </el-table-column>
    </el-table>
    <div class="button">
      <el-button @click="goBack">返回</el-button>
      <el-button @click="goedit" type="primary" :disabled="isAuthor">编辑题目</el-button>
      <el-button @click="godown" type="primary">下载</el-button>
      <el-button @click="upload" type="primary">批量上传</el-button>
      <el-button @click="gosave" type="primary" :disabled="isAuthor">保存</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, computed, onBeforeMount } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
import UpLoad from '@/components/UpLoad.vue'

export default defineComponent({
  props: {
    isAuthor: {
      type: Boolean
    }
  },
  name: 'FinalGrade',
  components: {
    UpLoad
  }
})
</script>

<script lang="ts" setup>
const store = useStore<GlobalDataProps>()
const router = useRouter()
const goUpload = ref(false)
const gradContent = computed(() => store.state.gradContent)
const info = computed(() => store.state.currentCourse)
onBeforeMount(() => {
  store.dispatch('getGradContent', info.value.id)
})
const goBack = () => {
  router.push('/')
}
const goedit = () => {
  console.log('edit')
}
const upload = () => {
  goUpload.value = true
  console.log(111)
}
const godown = () => {
  console.log('down')
}
const gosave = () => {
  console.log('gosave')
}
const data = [{}]
</script>
