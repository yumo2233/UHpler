<template>
  <div class="outer">
    <toast-box :message="message"></toast-box>
    <div class="header">
      <div class="dev"></div>
      <span>成绩占比</span>
    </div>
    <i>* </i><span>平时成绩占比：</span>
    <span class="right">
      <el-input @input="fchange" :disabled="isAuthor" type="number" v-model="usual" class="w-50 m-2" placeholder="请输入0-100以内数字" style="height: 38px;width: 343px;"/>&nbsp;%
    </span><br>
    <i>* </i><span>期末成绩占比：</span>
    <span class="right">
      <el-input @input="uchange" :disabled="isAuthor" type="number" v-model="final" class="w-50 m-2" placeholder="请输入0-100以内数字" style="height: 38px;width: 343px;"/>&nbsp;%
    </span><br>
    <template v-for="(i, index) in o" :key="i.id">
      <add-examway :index="+index+1"></add-examway>
    </template>
    <div>
      <el-button type="primary" :disabled="isAuthor" @click="addEaxmWay">增加考核方式</el-button>
      <el-button @click="back">返回</el-button>
      <el-button :disabled="isAuthor" type="primary" @click="handleSave">保存</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, computed, PropType } from 'vue'
// import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { ICourses } from '@/store'
import AddExamway from '@/components/aboutCourse/AddExamway.vue'
import ToastBox, { emitter } from '@/components/ToastBox.vue'

export default defineComponent({
  props: {
    info: {
      type: Object as PropType<ICourses>
    },
    isAuthor: {
      type: Boolean,
      default: true
    }
  },
  name: 'ExamWay',
  components: {
    AddExamway,
    ToastBox
  },
  setup (props) {
    // const store = useStore<GlobalDataProps>()
    const router = useRouter()
    const o = computed(() => props.info?.checkList)
    const usual = ref(props.info?.usualRatio || 0)
    const final = ref(props.info?.finalRatio || 0)
    const message = ref('')
    const addEaxmWay = () => {
      console.log(111)
    }
    const back = () => {
      emitter.emit('on-dialog-unsave', () => null)
      message.value = '是否返回课程列表页，返回将不会保存已修改信息'
    }
    const handleSave = () => {
      emitter.emit('on-dialog-open', () => null)
      message.value = '保存后将覆盖已有信息，请确认输入信息正确无误'
    }
    const uchange = () => {
      usual.value = 100 - final.value
    }
    const fchange = () => {
      final.value = 100 - usual.value
    }
    emitter.on('on-dialog-confirm', () => {
      // 打包数据
      router.push('/')
    })
    // const methods = reactive({store.state.nameHere})
    return {
      o,
      addEaxmWay,
      back,
      handleSave,
      usual,
      final,
      uchange,
      fchange,
      message
    }
  }
})
</script>

<style scoped>
.outer {
  user-select: none;
}
.header {
  position: relative;
}
.header span {
  margin-left: 8px;
}
i {
  color: #ed6355;
}
.dev {
  width: 4px;
  height: 25px;
  display: inline-block;
  background-color: #337ef7;
  position: absolute;
  top: 22px;
}
.right {
  position: absolute;
  left: 175px;
}
</style>
