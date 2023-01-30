import { createRouter, createWebHistory } from 'vue-router'

// 引入组件
import CourseManage from '@/pages/CourseManage.vue'
import CourseList from '@/pages/CourseList.vue'
import ClassManage from '@/pages/ClassManage.vue'
import LoginVue from '@/pages/aboutLogin/LoginVue.vue'
import FirstLogin from '@/pages/aboutLogin/FirstLogin.vue'

const routerHistory = createWebHistory()
const router = createRouter({
  history: routerHistory,
  routes: [{
    path: '/',
    name: 'courselist',
    component: CourseList
  }, {
    path: '/course',
    name: 'course',
    component: CourseManage
  }, {
    path: '/class',
    name: 'class',
    component: ClassManage
  }, {
    path: '/login',
    name: 'login',
    component: LoginVue
  }, {
    path: '/change',
    name: 'change',
    component: FirstLogin
  }]
})

export default router
