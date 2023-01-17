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
