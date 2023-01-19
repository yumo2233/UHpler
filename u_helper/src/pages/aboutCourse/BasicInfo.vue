<template>
  <div class="outer">
    <toast-box :message="message"></toast-box>
    <span><i>*</i> 所属院系：</span>
    <span class="right">
      <el-radio-group :disabled="isAuthor" v-model="academy">
        <el-radio :label="1">通信与信息工程学院</el-radio>
        <el-radio :label="2">电子工程学院</el-radio>
        <el-radio :label="3">自动化学院</el-radio>
        <el-radio :label="4">计算机学院</el-radio>
        <el-radio :label="5">经济与管理学院</el-radio>
      </el-radio-group>
    </span><br>
    <span><i>*</i> 开设单位：</span>
    <span class="right"><el-input v-model="unit" :disabled="isAuthor" maxlength="20" class="w-50 m-2" placeholder="请输入20字以内文字" style="height: 38px;width: 343px;"/></span><br>
    <span><i>*</i> 任课老师姓名：</span>
    <span class="right"><el-input v-model="teacher" :disabled="isAuthor" maxlength="20" class="w-50 m-2" placeholder="请输入20字以内文字" style="height: 38px;width: 343px;"/></span><br>
    <span><i>*</i> 课程名称：</span>
    <span class="right"><el-input v-model="name" :disabled="isAuthor" maxlength="20" class="w-50 m-2" placeholder="请输入20字以内文字" style="height: 38px;width: 343px;"/></span><br>
    <span><i>*</i> 课程性质：</span>
      <span class="right">
        <el-radio-group :disabled="isAuthor" v-model="courseKind" class="ml-4">
          <el-radio :label="11" size="large">专业必修</el-radio>
          <el-radio :label="12" size="large">专业选修</el-radio>
        </el-radio-group>
      </span><br>
    <span><i>*</i> 课程编号：</span>
    <span class="right"><el-input v-model="id" :disabled="isAuthor" class="w-50 m-2" maxlength="20" placeholder="请输入20字以内字符" style="height: 38px;width: 343px;"/></span><br>
    <span><i>*</i> 总学时：</span>
    <span class="right"><el-input v-model="allPeriod" type="number" :disabled="isAuthor" class="w-50 m-2" placeholder="请输入0-100以内纯数字" style="height: 38px;width: 343px;"/></span><br>
      <span><i>*</i> 理论学时：</span>
    <span class="right"><el-input v-model="theoryPeriod" :disabled="isAuthor" type="number" class="w-50 m-2" placeholder="请输入0-100以内纯数字" style="height: 38px;width: 343px;"/></span><br>
    <span><i>*</i> 实验学时：</span>
    <span class="right"><el-input v-model="runPeriod" type="number" :disabled="isAuthor" class="w-50 m-2" placeholder="请输入0-100以内纯数字" style="height: 38px;width: 343px;"/></span><br>
    <span><i>*</i> 学分：</span>
    <span class="right"><el-input v-model="score" :disabled="isAuthor" type="number" class="w-50 m-2" placeholder="请输入0-100以内纯数字" style="height: 38px;width: 343px;"/></span><br>
    <span><i>*</i> 先修课程：</span>
    <span class="right">
      <el-select v-model="courseKind" filterable="true" allow-create :disabled="isAuthor" class="m-2" placeholder="请输入" size="large" style="width: 343px;">
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </span><br>
    <span><i>*</i> 考核对象：</span>
    <span class="right">
      <el-cascader style="height: 38px;width: 343px;" :disabled="isAuthor" :options="assessment" :props="customProps" clearable />
    </span><br>
    <span><i>*</i> 学生人数：</span>
    <span class="right"><el-input :disabled="isAuthor" v-model="studentNum" type="number" class="w-50 m-2" placeholder="请输入0-100以内纯数字" style="height: 38px;width: 343px;"/></span><br>
    <span><i>*</i> 执行学期：</span>
    <span class="right">
      <el-select :disabled="isAuthor" v-model="courseKind" class="m-2" placeholder="请选择" size="large" style="width: 343px;">
        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </span><br>
    <el-button :diabled="isAuthor" @click="handleBack" style="margin: 0 20px;">返回</el-button>
    <el-button :diabled="isAuthor" @click="handleSave" type="primary" style="margin: 0 auto;">保存</el-button>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, PropType, onBeforeMount } from 'vue'
import { useStore } from 'vuex'
import { ICourses, GlobalDataProps } from '@/store'
import { useRouter } from 'vue-router'
import ToastBox, { emitter } from '@/components/ToastBox.vue'
import axios from 'axios'
import { apis } from '@/common/apis'
export default defineComponent({
  name: 'BasicInfo',
  props: {
    multiple: {
      default: true
    },
    info: {
      type: Object as PropType<ICourses>
    },
    isAuthor: {
      type: Boolean
    }
  },
  components: {
    ToastBox
  },
  setup (props) {
    let num = 0
    if (props.info) {
      switch (props.info.unit) {
        case 'communAndInfo': num = 1
          break
        case 'electronAndEng': num = 2
          break
        case 'autoMation': num = 3
          break
        case 'computing': num = 4
          break
      }
    }
    const router = useRouter()
    const store = useStore<GlobalDataProps>()
    // #region input
    const academy = ref(num || 1)
    const unit = ref(props.info?.unit || '')
    const teacher = ref(props.info?.teacher || '')
    const name = ref(props.info?.name || '')
    const courseKind = ref(11)
    const id = ref(props.info?.id || '')
    const allPeriod = ref(props.info?.allPeriod || 0)
    const theoryPeriod = ref(props.info?.theoryPeriod || 0)
    const runPeriod = ref(props.info?.runPeriod || 0)
    const score = ref(props.info?.score || 0)
    const studentNum = ref(props.info?.studentNum || 0)
    const assessment = store.state.classList
    const message = ref('')
    // const checkObj = getCheckedNodes
    const customProps = {
      multiple: true, // 启用多选
      emitPath: false, // 只返回该节点的值
      value: '', // 自定义当前数组的键名
      label: 'collegeEnum',
      children: 'child'
    }
    onBeforeMount(() => {
      axios.get(apis.classlist).then(res => {
        // eslint-disable-next-line no-constant-condition
        if (true) { // 改为res.status === 200 && res.data.success
          store.state.classList = res.data.content
        }
      })
    })
    const options = [
      {
        value: 'Option1',
        label: 'Option1'
      },
      {
        value: 'Option2',
        label: 'Option2'
      }
    ]
    // #endregion
    const handleSave = () => {
      // 身份验证
      // eslint-disable-next-line no-constant-condition
      if (true) {
        emitter.emit('on-dialog-open', () => null)
        message.value = '保存后将覆盖已有信息，请确认输入信息正确无误'
      }
    }
    const handleBack = () => {
      emitter.emit('on-dialog-unsave', () => null)
      message.value = '是否返回课程列表页，返回将不会保存已修改信息'
    }
    emitter.on('on-dialog-confirm', () => {
      const modifiedCours = {
        ...props.info,
        unit: unit.value,
        teacher: teacher.value,
        name: name.value,
        id: id.value,
        allPeriod: allPeriod.value,
        theoryPeriod: theoryPeriod.value,
        runPeriod: runPeriod.value,
        score: score.value,
        studentNum: studentNum.value
      }
      if (props.info) {
        axios.post(apis.modfiy, JSON.stringify(modifiedCours)).then(res => {
          console.log('props.info exits modefied', res.status)
        })
      } else {
        axios.post(apis.addone, modifiedCours).then(res => {
          if (res.status !== 200) {
            alert('保存失败')
          }
          console.log('props not exits add')
        })
      }
      router.push('/')
    })
    return {
      customProps,
      // checkObj,
      assessment,
      academy,
      unit,
      teacher,
      name,
      courseKind,
      id,
      allPeriod,
      theoryPeriod,
      runPeriod,
      score,
      studentNum,
      options,
      handleSave,
      handleBack,
      message,
      props: {
        multiple: true
      }
    }
  }
})
</script>

<style scoped>
.outer {
  width: 100%;
  position: relative;
}
i {
  color: #ed6355;
}
.right {
  position: absolute;
  left: 152px;
}
</style>
