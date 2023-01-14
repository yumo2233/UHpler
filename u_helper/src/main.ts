import { createApp } from 'vue'
import App from './App.vue'
// router
import router from '@/router/index'
// store
import store from '@/store'
// elementUI
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

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
