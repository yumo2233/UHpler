<template>
  <template v-for="(i, iIndex) in targetList" :key="i.id">
    <div>
      <h4>{{ i.name }}</h4>
      <template v-for="(j, jIndex) in checkList" :key="j.id">
        <el-checkbox v-model="isChecked[iIndex*len+jIndex]" @change="onChange(iIndex, jIndex)" :label="j.name" />
        <span style="margin-left: 20px;">
          <i class="red">*</i>
          <span>占同考核方式权重</span>
          <el-input v-model="j.ratio2" style="width: 50px;" class="w-50 m-2" max="1"/> <br>
          <!-- 考核次数
          <el-input v-model="j.time" style="width: 50px;" class="w-50 m-2" /> <br> -->
        </span>
      </template>
    </div>
  </template>
</template>

<script lang="ts">
import { defineComponent, computed, reactive } from 'vue'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
export default defineComponent({
  name: 'ChooseList'
})
</script>

<script lang="ts" setup>
const store = useStore<GlobalDataProps>()
const targetList = computed(() => store.state.currentCourse.targetList)
const checkList = computed(() => store.state.currentCourse.checkList)
const len = checkList.value.length
const isChecked: boolean[] = reactive([])
for (let i = 0, len = targetList.value.length; i < len; i++) {
  for (let j = 0, clen = checkList.value.length; j < clen; j++) {
    if (targetList.value[i].checkList) {
      for (let t = 0, tlen = targetList.value[i].checkList?.length || 0; t < tlen; t++) {
        if ((targetList.value[i].checkList as [])[t] === checkList.value[j]) {
          isChecked.push(true)
        }
      }
      isChecked.push(false)
    } else {
      isChecked.push(false)
    }
  }
}
const onChange = (i: number, j: number) => {
  // console.log(isChecked)
  if (isChecked[i * len + j]) {
    if (!targetList.value[i].checkList) {
      targetList.value[i].checkList = []
      targetList.value[i].checkList?.push(checkList.value[j])
    } else {
      targetList.value[i].checkList?.push(checkList.value[j])
    }
  } else {
    for (let t = 0, len = targetList.value[i].checkList?.length || 0; t < len; t++) {
      if ((targetList.value[i].checkList as [])[t] === checkList.value[j]) {
        targetList.value[i].checkList?.splice(t, 1)
      }
    }
  }
}
</script>
