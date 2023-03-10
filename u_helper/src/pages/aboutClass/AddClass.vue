<template>
  <!-- TODO -->
  <up-load :website="website" v-if="goUpload" @close-model="closeUpload"></up-load>
  <edit-vue v-if="goEdit" @close-model="closeEdit"></edit-vue>
  <div class="outer">
    <div class="classInfo">
      <span style="font-weight: bolder;">编辑班级信息</span>
      <div class="input">
        <i class="red">* </i><span>学院: </span>
        <el-select v-model="unit" class="m-2" placeholder="Select" style="width: 343px;margin: 20px 0;">
          <el-option v-for="item in unit1" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select> <br>
        <i class="red">* </i><span>专业: </span>
        <el-select v-model="pro" class="m-2" placeholder="Select" style="width: 343px;margin: 20px 0;">
          <el-option v-for="o in pro1" :key="o.value" :label="o.label" :value="o.value"></el-option>
        </el-select> <br>
        <i class="red">* </i><span>年级: </span>
        <el-input v-model="grade" class="w-50 m-2" style="height: 34px;width: 343px;margin: 20px 0;" placeholder="填入整数"/><br>
        <i class="red">* </i><span>班级: </span>
        <el-input v-model="class1" class="w-50 m-2" style="height: 34px;width: 343px;margin: 20px 0;" placeholder="填入整数"/><br>
      </div>
    </div>
    <div class="editStu">
      <span style="font-weight: bolder;">编辑班级成员信息</span> <br>
      <el-button type="primary" @click="goUpload=true">批量上传</el-button>
      <el-button type="primary" @click="goEdit=true">新增</el-button>
      <el-table :data="stu" style="width: 100%;margin-top: 20px;">
        <el-table-column prop="index" label="班级序号"/>
        <el-table-column prop="name" label="姓名"/>
        <el-table-column prop="number" label="学号"/>
        <el-table-column label="操作">
          <template #default="scope">
            <div>
              <el-button text @click="deleteCurrent(stu[scope.$index].number)">删除</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-button type="primary" @click="handleSave">保存</el-button>
  </div>
</template>

<script lang="ts">
import { defineComponent, computed, onBeforeMount, ref } from 'vue'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store/index'
import { apis } from '@/common/apis'
import UpLoad from '@/components/UpLoad.vue'
import EditVue from '@/components/EditVue.vue'
import axios from 'axios'
import router from '@/router'
export default defineComponent({
  name: 'AddClass',
  components: {
    UpLoad, EditVue
  },
  setup () {
    const store = useStore<GlobalDataProps>()
    const unit1 = computed(() => store.state.ClassAndPro.college)
    const pro1 = computed(() => store.state.ClassAndPro.professional)
    const stu = computed(() => store.state.stu)
    const unit = ref('')
    const pro = ref('')
    const class1 = ref(0)
    const grade = ref('')
    const goUpload = ref(false)
    const goEdit = ref(false)
    const closeUpload = () => {
      goUpload.value = false
    }
    const website = apis.addclassWeb + '?token=' + localStorage.getItem('token')
    // const edit = (id: number) => {
    //   goEdit.value = true
    // }
    const deleteCurrent = (id: number) => {
      store.commit('deleteAddStu', id)
    }
    const handleSave = () => {
      const obj = {
        college: (+unit.value - 1).toString(),
        professional: pro.value,
        grade: grade.value,
        name: class1.value,
        userId: store.state.user.userId,
        arrayList: store.state.stu
      }
      axios.post(apis.insetClass, JSON.stringify(obj)).then(() => {
        router.push('/class')
      })
    }
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const closeEdit = (obj: any) => {
      goEdit.value = false
      // 此处保存数据
      if (obj) {
        store.commit('addClassStu', obj)
      }
    }
    onBeforeMount(() => {
      store.dispatch('getGradeAndProfess')
    })
    return {
      unit1, pro1, unit, pro, class1, grade, goUpload, closeUpload, stu, goEdit, closeEdit, deleteCurrent, handleSave, website
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
