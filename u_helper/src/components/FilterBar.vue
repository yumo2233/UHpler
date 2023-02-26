<template>
  <div class="outer">
    <div class="academy">
      <span>学院/专业：</span>
      <el-select @change="updateVal" v-model="pro" class="m-2" placeholder="Select">
        <el-option v-for="item in professional" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </div>
    <div class="grade">
      <span>年级：</span>
      <el-select @change="updateVal" v-model="gra" class="m-2" placeholder="Select">
        <el-option v-for="o in grade" :key="o.value" :label="o.label" :value="o.value"/>
      </el-select>
    </div>
    <div class="button">
      <el-button @click="search" type="primary">搜索</el-button>
      <el-button @click="addNew" type="primary">新增</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onBeforeMount, computed } from 'vue'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
import router from '@/router'
export default defineComponent({
  name: 'FilterBar',
  emits: ['update'],
  setup (props, context) {
    const store = useStore<GlobalDataProps>()
    onBeforeMount(() => {
      store.dispatch('getGradeAndProfess')
    })
    const pro = ref('')
    const gra = ref('')
    const professional = computed(() => store.state.ClassAndPro.professional)
    const grade = computed(() => store.state.ClassAndPro.grade)
    const updateVal = () => {
      const onj = { grade: gra.value, profess: pro.value }
      context.emit('update', onj)
    }
    const addNew = () => {
      router.push('/addclass')
    }
    const search = () => {
      console.log(222)
    }
    return {
      pro, professional, addNew, search, gra, grade, updateVal
    }
  }
})
</script>

<style scoped>
.outer {
  padding-top: 20px;
  width: 100%;
  background-color: #fff;
  border-radius: 10px;
  height: 130px;
  user-select: none;
}
.academy, .grade {
  display: inline-block;
  margin-right: 100px;
  margin-left: 50px;
}
.button {
  margin-top: 30px;
  margin-left: 50px;
}
</style>
