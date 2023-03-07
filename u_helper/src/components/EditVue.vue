<template>
  <teleport to='#model'>
    <div class="outer">
      <div class="inner">
        <span style="font-weight: bolder;">编辑班级成员信息</span> <br>
        <i class="red">* </i>班内序号：
        <el-input v-model="index" style="height: 34px;width: 343px;margin: 20px 0;"/><br>
        <i class="red">* </i>姓名：
        <el-input v-model="name" style="height: 34px;width: 343px;margin: 20px 0;"/><br>
        <i class="red">* </i>学号：
        <el-input v-model="id" style="height: 34px;width: 343px;margin: 20px 0;"/><br>
        <div class="button">
          <el-button @click="save" type="primary">保存</el-button>
          <el-button @click="back" type="plain">取消</el-button>
        </div>
      </div>
    </div>
  </teleport>
</template>

<script lang="ts">
import { ref, defineComponent, computed } from 'vue'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
export default defineComponent({
  name: 'UpLoad',
  emits: {
    'close-model': null
  },
  props: {
    edit: Number,
    classId: Number
  },
  setup (props, context) {
    const store = useStore<GlobalDataProps>()
    const index = ref(0)
    const name = ref('')
    const id = ref(0)
    if (props.edit) {
      const stu = computed(() => store.state.stuArr.find(o => o.id === props.edit))
      index.value = stu.value?.index as number
      name.value = stu.value?.name as string
      id.value = stu.value?.id as number
    }
    const save = () => {
      const obj = {
        name: name.value,
        index: index.value,
        number: id.value,
        id: Date.now()
      }
      context.emit('close-model', obj)
    }
    const back = () => {
      context.emit('close-model')
    }
    return {
      save, back, index, name, id
    }
  }
})
</script>

<style scoped>
.outer {
  background-color: rgba(0, 0, 0, .3);
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  z-index: 100;
  line-height: 70px;
  user-select: none;
}
.inner {
  background-color: #bfa;
  width: 800px;
  margin: 0 auto;
  margin-top: 150px;
  border-radius: 10px;
}
</style>
