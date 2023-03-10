import { createApp } from 'vue'
import App from './App.vue'
// router
import router from '@/router/index'
// store
import store from '@/store'
// axios
import axios from 'axios'
// elementUI
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

axios.defaults.baseURL = 'https://mock.apifox.cn/m1/2206130-0-default'
// axios.defaults.baseURL = 'http://192.168.0.111:8888'
// axios.defaults.baseURL = 'http://124.223.18.54:8080'
if (localStorage.getItem('token')) {
  store.state.user.isLogin = true
  store.state.user.userId = parseInt(localStorage.getItem('userId') as string)
  store.state.user.number = localStorage.getItem('number') as string
  axios.defaults.headers.common.token = localStorage.getItem('token')
}
axios.defaults.headers.post['Content-Type'] = 'application/json'

const app = createApp(App)
// store
app.use(store)
// router
app.use(router)
// elementUI
app.use(ElementPlus)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.mount('#app')
