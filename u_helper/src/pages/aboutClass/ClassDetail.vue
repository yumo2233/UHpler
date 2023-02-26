<template>
  <edit-vue :edit="outerId" v-if="isEdit" :classId="id" @close-model="isEdit = false"></edit-vue>
  <div class="outer">
    <div class="classInfo">
      <span style="font-weight: bolder;">编辑班级信息</span>
      <div class="input">
        <i class="red">* </i><span> 学院: </span>
        <el-select :disabled="view" @change="edited=true" v-model="info.college" class="m-2" placeholder="Select" style="width: 343px;margin: 20px 0;">
          <el-option v-for="item in unit" :key="item.value" :label="item.label" :value="item.value"/>
        </el-select> <br>
        <i class="red">* </i><span> 专业: </span>
        <el-select :disabled="view" @change="edited=true" v-model="info.professional" class="m-2" placeholder="Select" style="width: 343px;margin: 20px 0;">
          <el-option v-for="o in pro" :key="o.value" :label="o.label" :value="o.value"/>
        </el-select> <br>
        <i class="red">* </i><span> 年级: </span>
        <el-input :disabled="view" @input="edited=true" class="w-50 m-2" v-model="info.grade" style="height: 34px;width: 343px;margin: 20px 0;"/><br>
        <i class="red">* </i><span> 班级: </span>
        <el-input :disabled="view" @input="edited=true" class="w-50 m-2" v-model="info.name" style="height: 34px;width: 343px;margin: 20px 0;"/><br>
      </div>
    </div>
    <div class="editStu">
      <span style="font-weight: bolder;">编辑班级成员信息</span> <br>
      <el-table :data="stu" style="width: 100%;margin-top: 20px;">
        <el-table-column prop="index" label="班级序号"/>
        <el-table-column prop="name" label="姓名"/>
        <el-table-column prop="number" label="学号"/>
        <el-table-column label="操作">
          <template #default="scope">
            <div>
              <el-button :disabled="view" text @click="edit(stu[scope.$index].id)">编辑</el-button>
              <el-button :disabled="view" text @click="deleteCurrent(stu[scope.$index].id)">删除</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="button">
      <el-button type="plain" @click="back">返回</el-button>
      <el-button type="primary" @click="handleSave">保存</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, computed, ref, onBeforeMount } from 'vue'
import EditVue from '@/components/EditVue.vue'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
import { useRoute } from 'vue-router'
import { apis } from '@/common/apis'
import axios from 'axios'
import router from '@/router'
export default defineComponent({
  name: 'ClassDetail',
  components: {
    EditVue
  },
  setup () {
    const route = useRoute()
    const store = useStore<GlobalDataProps>()
    const view = route.query.view ? 1 : 0
    console.log(view)
    const id = route.query.id ? +route.query.id : 0
    const unit = computed(() => store.state.ClassAndPro.grade)
    const pro = computed(() => store.state.ClassAndPro.professional)
    const info = computed(() => store.getters.classDetail(id))
    const stu = computed(() => store.state.stuArr)
    const edited = ref(false)
    const goUpload = ref(false)
    const isEdit = ref(false)
    const outerId = ref(0)// 给editvue用的学生信息
    const handleSave = () => {
      if (edited.value) {
        axios.post(apis.updateClass, info.value)
      }
      router.push('/class')
    }
    const deleteCurrent = (id: number) => {
      if (confirm('确认删除?')) {
        axios.get(`${apis.deleteSingleStudent}/${id}`)
        store.dispatch('getStuInfo', id)
      }
    }
    const edit = (id: number) => {
      isEdit.value = true
      outerId.value = id
    }
    const addOne = () => {
      isEdit.value = true
    }
    const back = () => {
      router.push('/class')
    }
    onBeforeMount(() => {
      store.dispatch('getStuInfo', id)
    })
    return {
      view, info, unit, pro, edited, stu, handleSave, deleteCurrent, edit, goUpload, isEdit, addOne, outerId, id, back
    }
  }
})
</script>

<style scoped>
.outer {
  margin-left: 10px;
}
.classInfo {
  background-color: #f5f6f7;
  padding: 10px;
  border-radius: 10px;
}
.input {
  margin-top: 20px;
  margin-left: 40px;
}
.editStu {
  padding: 10px;
  margin-top: 40px;
  border-radius: 10px;
  background-color: #f5f6f7;
}
.button {
  width: 132px;
  margin: 20px auto 0;
}
</style>
