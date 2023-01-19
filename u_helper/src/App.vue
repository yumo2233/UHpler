<template>
  <template v-if="false">
    <login-vue></login-vue>
  </template>
  <template v-else>
    <lay-out></lay-out>
  </template>
</template>

<script lang="ts">
import '@/common/style.css'
import { defineComponent } from 'vue'
// 组件
import LayOut from '@/components/LayOut.vue'
import LoginVue from '@/pages/aboutLogin/LoginVue.vue'
export default defineComponent({
  name: 'App',
  components: {
    LayOut,
    LoginVue
  }
})
</script>
