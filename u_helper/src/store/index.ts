import { createStore, Commit } from 'vuex'
import axios from 'axios'
import { apis } from '@/common/apis'

export interface ICourses {
  name: string
  teacher: string
  college: string
  unit: string
  id: number
  studentNum: number
  nature: string
  allPeriod: number
  runPeriod: number
  theoryPeriod: number
  score: number
  preList: number[]
  preElse: null | []
  classList: number[]
  semester: string
  usualRatio: number
  finalRatio: number
  finalId: number
  userId: number
  userName: string
  number: string
  checkList: {
    id: number,
    courseId: number
  }
}
interface IAccount {
  isFirst?: boolean
  userAvator?: string
  userId: number
}
interface ICurrentUser {
  id: number;
  userName: string;
  userId: number;
  Tcourses: ICourses[];
  avator: string;
}
export interface GlobalDataProps{
  isLogin: boolean
  user: ICurrentUser
  token?: string
  courses: ICourses[]
  accountInfo: IAccount
  classList: []
}

const getAndCommit = async (url: string, mutationName: string, commit: Commit) => {
  const { data } = await axios.get(url)
  commit(mutationName, data)
}

export default createStore({
  state: {
    token: localStorage.getItem('token') || '',
    isLogin: false,
    courses: [],
    classList: []
  },
  getters: {
  },
  mutations: {
    getCourse (state, rawData) {
      // if (rawData.success) {
      //   state.courses = rawData.content
      // } else {
      //   alert('获取课程信息失败，请刷新重试')
      // }
      state.courses = rawData.content
    }
  },
  actions: {
    getCourse ({ commit }) {
      getAndCommit(apis.course, 'getCourse', commit)
    }
  }
})
