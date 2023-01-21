import { createStore, Commit } from 'vuex'
import axios from 'axios'
import { apis } from '@/common/apis'

interface checkArray {
  id: number,
  courseId: number,
  ratio: number,
  name: string
}
interface targetArray {
  id: number,
  courseId: number,
  content: string,
  number: string,
  name: string,
  graduateId: number
}
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
  checkList: checkArray[]
  targetList: targetArray[]
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
  currentCourse: ICourses
  graduationList: []
  isAdd: boolean
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
    classList: [],
    currentCourse: {
      name: '',
      teacher: '',
      college: '',
      unit: '',
      id: 0,
      studentNum: 0,
      nature: '',
      allPeriod: 0,
      runPeriod: 0,
      theoryPeriod: 0,
      score: 0,
      preList: [],
      preElse: [],
      classList: [],
      semester: '',
      usualRatio: 0,
      finalRatio: 0,
      finalId: 0,
      userId: 0,
      userName: '',
      number: '',
      checkList: [],
      targetList: []
    },
    graduationList: [],
    isAdd: false
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
    },
    getClassList (state, rawData) {
      state.classList = rawData.content
    },
    addCurrentCourse (state, rawData) {
      state.currentCourse = rawData
    },
    clearCurrentCourse (state) {
      state.currentCourse = {
        name: '',
        teacher: '',
        college: '',
        unit: '',
        id: 0,
        studentNum: 0,
        nature: '',
        allPeriod: 0,
        runPeriod: 0,
        theoryPeriod: 0,
        score: 0,
        preList: [],
        preElse: [],
        classList: [],
        semester: '',
        usualRatio: 0,
        finalRatio: 0,
        finalId: 0,
        userId: 0,
        userName: '',
        number: '',
        checkList: [],
        targetList: []
      }
    },
    addGraduationList (state, rawData) {
      state.graduationList = rawData
    },
    addOne (state) {
      state.isAdd = true
    },
    noAddOne (state) {
      state.isAdd = false
    }
  },
  actions: {
    getCourse ({ commit }) {
      getAndCommit(apis.course, 'getCourse', commit)
    },
    getClassList ({ commit }) {
      getAndCommit(apis.classlist, 'getClassList', commit)
    }
  }
})
