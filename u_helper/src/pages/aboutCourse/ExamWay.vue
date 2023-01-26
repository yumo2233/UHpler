<template>
  <div class="outer">
    <el-dialog v-model="centerDialogVisible" title="Warning" width="30%" center>
    <span>
      {{ message }}
    </span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSave3">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
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
    <template v-for="(i, index) in info.checkList" :key="i.id">
      <add-examway :index="+index+1" :info="i"></add-examway>
    </template>
    <div>
      <el-button type="primary" :disabled="isAuthor" @click="addEaxmWay">增加考核方式</el-button>
      <el-button @click="back">返回</el-button>
      <el-button :disabled="isAuthor" type="primary" @click="handleSave4">保存</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { GlobalDataProps } from '@/store'
import AddExamway, { Aemitter } from '@/components/aboutCourse/AddExamway.vue'
import axios from 'axios'
import { apis } from '@/common/apis'
console.log(5)

export default defineComponent({
  props: {
    isAuthor: {
      type: Boolean,
      default: true
    }
  },
  name: 'ExamWay',
  components: {
    AddExamway
  },
  setup () {
    const store = useStore<GlobalDataProps>()
    const router = useRouter()
    const info = computed(() => store.state.currentCourse)
    const addEaxmWay = () => {
      info.value.checkList.push({
        id: Date.now(),
        courseId: Date.now(),
        ratio: 0,
        name: ''
      })
    }
    const backOrSave = ref(0)
    const usual = computed({
      get: () => info.value.usualRatio,
      set: (value) => {
        store.commit('modeUsual', value)
      }
    })
    const final = computed({
      get: () => info.value.finalRatio,
      set: (value) => {
        store.commit('modelFinal', value)
      }
    })
    const message = ref('')
    const centerDialogVisible = ref(false)
    const back = () => {
      centerDialogVisible.value = true
      message.value = '是否返回课程列表页，返回将不会保存已修改信息'
    }
    const submitChange = () => {
      if (!store.state.isAdd) {
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
      }
      router.push('/')
      store.commit('noAddOne')
    }
    const handleSave3 = () => {
      if (backOrSave.value) {
        backOrSave.value = 0
        let sum = 0
        for (let i = 0, len = info.value.checkList.length; i < len; i++) {
          console.log(len)
          sum += +info.value.checkList[i].ratio
        }
        if (sum !== 100) {
          console.log('sum!==100')
          if (!confirm('当前所有考核方式比重相加不等于1,是否继续保存')) {
            console.log('quit')
            return false
          } else {
            submitChange()
          }
        } else {
          submitChange()
        }
      }
      router.push('/')
    }
    const handleSave4 = () => {
      backOrSave.value = 1
      centerDialogVisible.value = true
      message.value = '保存后将覆盖已有信息，请确认输入信息正确无误'
    }
    const uchange = () => {
      usual.value = 100 - final.value
    }
    const fchange = () => {
      final.value = 100 - usual.value
    }
    Aemitter.on('on-item-delete', (id: number) => {
      const len = info.value.checkList.length
      if (len < 2) {
        alert('至少保留一个考核方式！')
      } else {
        for (let i = 0; i < len; i++) {
          if (info.value.checkList[i].id === id) {
            info.value.checkList.splice(i, 1)
            break
          }
        }
      }
    })
    return {
      centerDialogVisible,
      info,
      addEaxmWay,
      back,
      handleSave4,
      handleSave3,
      usual,
      final,
      uchange,
      fchange,
      message
    }
  }
})
console.log(6)
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
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
