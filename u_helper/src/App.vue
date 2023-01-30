<template>
  <template v-if="!isLogin">
  <!-- <template v-if="!isLogin || isFirst"> -->
    <login-vue v-if="!isLogin"></login-vue>
    <first-login v-else></first-login>
  </template>
  <template v-else>
    <lay-out></lay-out>
  </template>
</template>

<script lang="ts">
import '@/common/style.css'
import { defineComponent, computed, onMounted } from 'vue'
import { useStore } from 'vuex'
import { GlobalDataProps } from './store'
import axios from 'axios'
// 组件
import LayOut from '@/components/LayOut.vue'
import LoginVue from '@/pages/aboutLogin/LoginVue.vue'
import FirstLogin from '@/pages/aboutLogin/FirstLogin.vue'
export default defineComponent({
  name: 'App',
  components: {
    LayOut,
    LoginVue,
    FirstLogin
  },
  setup () {
    const store = useStore<GlobalDataProps>()
    const user = computed(() => store.state.user)
    const isLogin = computed(() => user.value.isLogin)
    const isFirst = computed(() => user.value.isFirst)
    const token = computed(() => store.state.token)
    onMounted(() => {
      if (token.value && !user.value.isLogin) {
        axios.defaults.headers.common.Authorization = token.value
        store.dispatch('login')
      }
    })
    return {
      isLogin,
      isFirst
    }
  }
})
</script>
