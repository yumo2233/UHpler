<template>
  <div class="outer">
    <div class="left">
      <img src="../../assets/login.jpg" alt="accomplished by allspark studio">
    </div>
    <div class="right">
      <h3>首次登录请修改密码</h3>
      <el-input v-model="pas" placeholder="请输入新密码" size="large" show-password style="margin-bottom: 10px;"/>
      <el-input v-model="pass" placeholder="请确认密码" size="large" style="margin-top: 20px;margin-bottom: 10px;" show-password @input="isPass"/>
      <span class="red pos" v-show="nopass">两次输入密码不一致</span>
      <el-button :disabled="dis" @click="change" class="button" type="primary">确 定 修 改</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import axios from 'axios'
import { apis } from '@/common/apis'
import { defineComponent, ref, computed } from 'vue'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
import { useRouter } from 'vue-router'
export default defineComponent({
  name: 'FirstLogin'
})
</script>

<script lang="ts" setup>
const router = useRouter()
const store = useStore<GlobalDataProps>()
const id = computed(() => store.state.user.number)
const pas = ref('')
const pass = ref('')
const dis = ref(true)
const isPass = () => {
  if (pass.value) {
    if (pass.value === pas.value) {
      nopass.value = false
      dis.value = false
      return
    }
  }
  nopass.value = true
}
const nopass = ref(false)
const change = () => {
  axios.post(apis.change, JSON.stringify({ number: id.value, password: pass.value })).then(res => {
    if (res.data.success) {
      router.push('/')
    } else {
      alert('修改密码失败，请检查网络')
    }
  })
}
</script>

<style scoped>
.outer {
  width: 800px;
  height: 500px;
  margin: 0 auto;
  display: flex;
  margin-top: 100px;
  user-select: none;
}
img {
  width: 400px;
  margin-top: 50px;
}
.right {
  border: 2px solid #f5f6f7;
  box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
  width: 400px;
  height: 400px;
  border-radius: 18px;
  margin-top: 50px;
  padding: 20px;
  box-sizing: border-box;
  position: relative;
  right: 0;
  position: relative;
}
h3 {
  text-align: center;
  margin-bottom: 40px;
}
.button {
  color: white;
  font-weight: bolder;
  font-size: 18px;
  height: 40px;
  width: 300px;
  margin-left: 25px;
  position: absolute;
  bottom: 40px;
  left: 0;
  right: 0;
  margin: 0 auto;
}
.pos {
  position: absolute;
  left: 25px;
  bottom: 60px;
}
</style>
