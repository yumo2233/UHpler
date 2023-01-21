<template>
  <div class="outer">
    <el-dialog v-model="centerDialogVisible" title="Warning" width="30%" center>
    <span>
      {{ message }}
    </span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSave2">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>
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
    <span class="right">
      <el-input v-model="unit" :disabled="isAuthor" maxlength="20" class="w-50 m-2" placeholder="请输入20字以内文字" style="height: 38px;width: 343px;"/></span><br>
    <span><i>*</i> 任课老师姓名：</span>
    <span class="right">
      <el-input v-model="teacher" :disabled="isAuthor" maxlength="20" class="w-50 m-2" placeholder="请输入20字以内文字" style="height: 38px;width: 343px;"/></span><br>
    <span><i>*</i> 课程名称：</span>
    <span class="right">
      <el-input v-model="name" :disabled="isAuthor" maxlength="20" class="w-50 m-2" placeholder="请输入20字以内文字" style="height: 38px;width: 343px;"/></span><br>
    <span><i>*</i> 课程性质：</span>
      <span class="right">
        <el-radio-group :disabled="isAuthor" v-model="courseKind" class="ml-4">
          <el-radio :label="11" size="large">专业必修</el-radio>
          <el-radio :label="12" size="large">专业选修</el-radio>
        </el-radio-group>
      </span><br>
    <span><i>*</i> 课程编号：</span>
    <span class="right">
      <el-input v-model="id" :disabled="isAuthor" class="w-50 m-2" maxlength="20" placeholder="请输入20字以内字符" style="height: 38px;width: 343px;"/></span><br>
    <span><i>*</i> 总学时：</span>
    <span class="right">
      <el-input v-model="allPeriod" type="number" :disabled="isAuthor" class="w-50 m-2" placeholder="请输入0-100以内纯数字" style="height: 38px;width: 343px;"/></span><br>
      <span><i>*</i> 理论学时：</span>
    <span class="right">
      <el-input v-model="theoryPeriod" :disabled="isAuthor" type="number" class="w-50 m-2" placeholder="请输入0-100以内纯数字" style="height: 38px;width: 343px;"/></span><br>
    <span><i>*</i> 实验学时：</span>
    <span class="right">
      <el-input v-model="runPeriod" type="number" :disabled="isAuthor" class="w-50 m-2" placeholder="请输入0-100以内纯数字" style="height: 38px;width: 343px;"/></span><br>
    <span><i>*</i> 学分：</span>
    <span class="right">
      <el-input v-model="score" :disabled="isAuthor" type="number" class="w-50 m-2" placeholder="请输入0-100以内纯数字" style="height: 38px;width: 343px;"/></span><br>
    <span><i>*</i> 先修课程：</span>
    <span class="right">
      <el-select v-model="courseKind" filterable="true" allow-create :disabled="isAuthor" class="m-2" placeholder="请输入" size="large" style="width: 343px;">
        <el-option v-for="item in option1s" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </span><br>
    <span><i>*</i> 考核对象：</span>
    <span class="right">
      <el-cascader @change="updateValue" v-model="checkObj" ref="cascaderRef" :show-all-levels="false" :props="customProps" style="width: 343px;" :disabled="isAuthor" :options="options" clearable />
    </span><br>
    <span><i>*</i> 学生人数：</span>
    <span class="right">
      <el-input :disabled="isAuthor" v-model="studentNum" type="number" class="w-50 m-2" placeholder="请输入0-100以内纯数字" style="height: 38px;width: 343px;"/></span><br>
    <span><i>*</i> 执行学期：</span>
    <span class="right">
      <el-select :disabled="isAuthor" v-model="courseKind" class="m-2" placeholder="请选择" size="large" style="width: 343px;">
        <el-option v-for="item in option1s" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </span><br>
    <el-button :diabled="isAuthor" @click="handleBack" style="margin: 0 20px;">返回</el-button>
    <el-button :diabled="isAuthor" @click="handleSave1" type="primary" style="margin: 0 auto;">保存</el-button>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onBeforeMount, computed, Ref, watch } from 'vue'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
import { useRouter } from 'vue-router'
import axios from 'axios'
import { apis } from '@/common/apis'
export default defineComponent({
  name: 'BasicInfo',
  props: {
    multiple: {
      default: true
    },
    isAuthor: {
      type: Boolean
    }
  },
  setup () {
    const router = useRouter()
    const store = useStore<GlobalDataProps>()
    const info = computed(() => store.state.currentCourse)
    let num = 0
    if (info.value) {
      switch (info.value.unit) {
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
    const cascaderRef: Ref = ref(null)
    // #region input
    const academy = ref(num || 1)
    const unit = ref(info.value.unit || '')
    const teacher = ref(info.value.teacher || '')
    const name = ref(info.value.name || '')
    const courseKind = ref(11)
    const id = ref(info.value.number || '')
    const allPeriod = ref(info.value.allPeriod || 0)
    const theoryPeriod = ref(info.value.theoryPeriod || 0)
    const runPeriod = ref(info.value.runPeriod || 0)
    const score = ref(info.value.score || 0)
    const studentNum = ref(info.value.studentNum || 0)
    const message = ref('')
    const checkObj = ref(info.value.classList || null)
    const centerDialogVisible = ref(false)
    const backOrSave = ref(0)
    onBeforeMount(() => {
      store.dispatch('getClassList')
    })
    const options = computed(() => store.state.classList)
    const customProps = {
      multiple: true,
      emitPath: true
    }
    watch([unit, teacher, name, id, allPeriod, theoryPeriod, runPeriod, score, studentNum, message], () => {
      info.value.unit = unit.value
      info.value.teacher = teacher.value
      info.value.name = name.value
      info.value.id = +id.value
      info.value.allPeriod = allPeriod.value
      info.value.theoryPeriod = theoryPeriod.value
      info.value.runPeriod = runPeriod.value
      info.value.score = score.value
      info.value.studentNum = studentNum.value
    })
    const updateValue = () => {
      try {
        // eslint-disable-next-line @typescript-eslint/no-explicit-any
        const checked = (cascaderRef.value?.getCheckedNodes(true) as []).map((ele: any) => ele.value)
        info.value.checkList = checked
      } catch (err) {
        alert('至少选择一项考核对象')
      }
    }
    // console.log(cascaderRef.value.getCheckedNodes())
    const option1s = [
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
    const handleSave1 = () => {
      // 身份验证
      // eslint-disable-next-line no-constant-condition
      if (true) {
        backOrSave.value = 1
        centerDialogVisible.value = true
        message.value = '保存后将覆盖已有信息，请确认输入信息正确无误'
      }
    }
    const handleSave2 = () => {
      if (backOrSave.value) {
        backOrSave.value = 0
        if (!store.state.isAdd) {
          axios.post(apis.modfiy, JSON.stringify(info.value)).then(res => {
            console.log('props.info exits modefied', res.status)
          })
        } else {
          axios.post(apis.addone, JSON.stringify(info.value)).then(res => {
            if (res.status !== 200) {
              alert('保存失败')
            }
            console.log('props not exits add')
          })
        }
        store.commit('noAddOne')
      }
      router.push('/')
    }
    const handleBack = () => {
      centerDialogVisible.value = true
      message.value = '是否返回课程列表页，返回将不会保存已修改信息'
    }
    return {
      updateValue,
      centerDialogVisible,
      cascaderRef,
      customProps,
      checkObj,
      options,
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
      option1s,
      handleSave1,
      handleSave2,
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
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
