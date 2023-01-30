<template>
  <div class="outer">
    <div class="left">
      <img src="../../assets/login.jpg" alt="accomplished by allspark studio">
    </div>
    <div class="right">
      <h3>欢迎登录U助教系统</h3>
      <el-input v-model="userName" placeholder="请输入您的工号" clearable size="large" style="margin-bottom: 10px;" @blur="isUser"/>
      <span class="red pos1" v-show="usererror">{{ umessage }}</span>
      <el-input v-model="password" placeholder="请输入登录密码" size="large" style="margin-top: 20px;margin-bottom: 10px;" show-password @blur="isPass"/>
      <span class="red pos2" v-show="passerror">{{ pmessage }}</span>
      <el-button @click="login" class="button" type="primary">登 录</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import store from '@/store'
import { defineComponent, ref } from 'vue'
export default defineComponent({
  name: 'LoginVue',
  components: {
  },
  setup () {
    const userName = ref('')
    const password = ref('')
    const usererror = ref(false)
    const umessage = ref('')
    const pmessage = ref('')
    const passerror = ref(false)
    const isUser = () => {
      if (!userName.value) {
        usererror.value = true
        umessage.value = '工号不能为空！'
      } else {
        usererror.value = false
      }
    }
    const isPass = () => {
      if (!password.value) {
        passerror.value = true
        pmessage.value = '密码不能为空！'
      } else {
        passerror.value = false
      }
    }
    const login = () => {
      if (!passerror.value && !usererror.value && userName.value && password.value) {
        const info = JSON.stringify({ number: userName.value, password: password.value })
        store.dispatch('login', info)
      } else {
        return false
      }
    }
    return {
      userName,
      password,
      usererror,
      passerror,
      isPass,
      isUser,
      umessage,
      pmessage,
      login
    }
  }
})
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
.pos1 {
  position: absolute;
  left: 25px;
  bottom: 140px;
}
.pos2 {
  /* background-color: red; */
  position: absolute;
  left: 25px;
  bottom: 60px;
}
</style>
