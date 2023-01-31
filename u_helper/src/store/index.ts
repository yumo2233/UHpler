import { createStore, Commit } from 'vuex'
import axios from 'axios'
import { apis } from '@/common/apis'

export interface checkArray {
  id: number,
  courseId: number,
  ratio: number,
  name: string,
  ratio2: number,
  time: number
}
type checkName = 'id' | 'courseId' | 'ratio' | 'name'
interface targetArray {
  id: number,
  courseId: number,
  content: string,
  number: string,
  name: string,
  graduateId: number,
  checkList?: checkArray[]
}
type targetName = 'id' | 'courseId' | 'content' | 'number' | 'name' | 'graduateId'
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

interface stuProps {
  name: string
  id: number
  classId: number
  className: string
  number: number
  index: number
  usualScore: number[]
  finalScore: number[]
}

// interface IAccount {
//   isFirst?: boolean
//   userAvator?: string
//   userId: number
// }
interface ICurrentUser {
  number: number;
  isFirst?: boolean;
  userName: string;
  Tcourses: ICourses[];
  avator: string;
  isLogin: boolean;
}
export interface GlobalDataProps{
  isLogin: boolean
  user: ICurrentUser
  token?: string
  courses: ICourses[]
  // accountInfo: IAccount
  classList: []
  currentCourse: ICourses
  graduationList: []
  stuGrade: stuProps[]
  isAdd: boolean
}

const getAndCommit = async (url: string, mutationName: string, commit: Commit) => {
  const { data } = await axios.get(url)
  commit(mutationName, data)
}

// eslint-disable-next-line @typescript-eslint/no-explicit-any
const checkAndCommit = (state: { token?: string; isLogin?: boolean; courses?: never[]; classList?: never[]; currentCourse: any; graduationList?: never[]; isAdd?: boolean }, name: checkName, id: number, value: string | number) => {
  const tmp = state.currentCourse.checkList.find((item: checkArray) => (item as checkArray).id === id)
  if (tmp) {
    (tmp[name] as number | string) = value
  }
}

// eslint-disable-next-line @typescript-eslint/no-explicit-any
const examAndCommit = (state: { token?: string; isLogin?: boolean; courses?: never[]; classList?: never[]; currentCourse: any; graduationList?: never[]; isAdd?: boolean }, name: targetName, id: number, value: string | number) => {
  const tmp = state.currentCourse.targetList.find((item: targetArray) => (item as targetArray).id === id)
  if (tmp) {
    (tmp[name] as number | string) = value
  }
}
export default createStore({
  state: {
    user: { isLogin: true, isFirst: false },
    token: localStorage.getItem('token') || '',
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
    stuGrade: [],
    isAdd: false
  },
  getters: {
    totalScore: (state) => (index: number) => {
      let score = 0
      const targetList = (state.currentCourse.targetList[index] as targetArray).checkList
      const len = targetList?.length || 0
      for (let i = 0; i < len; i++) {
        score += 100 * ((targetList as [])[i] as checkArray).ratio2 * ((targetList as [])[i] as checkArray).ratio
      }
      return score / 10000
    },
    stuScore: (state) => (index: number, stuIndex: number) => {
      let score = 0
      const checkList = state.currentCourse.checkList
      const targetList = (state.currentCourse.targetList[index] as targetArray).checkList
      const len = targetList?.length || 0
      const stuInfo = (state.stuGrade[stuIndex] as stuProps).usualScore
      for (let i = 0; i < len; i++) {
        const currentTotal = stuInfo[checkList.indexOf((targetList as [])[i])]
        score += currentTotal * ((targetList as [])[i] as checkArray).ratio2 * ((targetList as [])[i] as checkArray).ratio
      }
      return score / 10000
    }
  },
  mutations: {
    // #region
    modeUnit (state, rawData) {
      state.currentCourse.unit = rawData
    },
    modeTeacher (state, rawData) {
      state.currentCourse.teacher = rawData
    },
    modeName (state, rawData) {
      state.currentCourse.name = rawData
    },
    modeId (state, rawData) {
      state.currentCourse.id = rawData
    },
    modeAllP (state, rawData) {
      state.currentCourse.allPeriod = rawData
    },
    modeTheory (state, rawData) {
      state.currentCourse.theoryPeriod = rawData
    },
    modeRunP (state, rawData) {
      state.currentCourse.runPeriod = rawData
    },
    modeScore (state, rawData) {
      state.currentCourse.score = rawData
    },
    modeStuNum (state, rawData) {
      state.currentCourse.studentNum = rawData
    },
    modeObj (state, rawData) {
      state.currentCourse.checkList = rawData
    },
    modeUsual (state, rawData) {
      state.currentCourse.usualRatio = rawData
    },
    modelFinal (state, rawData) {
      state.currentCourse.finalRatio = rawData
    },
    updateName (state, rawData) {
      checkAndCommit(state, 'name', rawData.index, rawData.value)
    },
    updateRatio (state, rawData) {
      checkAndCommit(state, 'ratio', rawData.index, rawData.value)
    },
    updateExName (state, rawData) {
      examAndCommit(state, 'name', rawData.index, rawData.value)
    },
    updateExId (state, rawData) {
      examAndCommit(state, 'id', rawData.index, rawData.value)
    },
    updateExTar (state, rawData) {
      examAndCommit(state, 'content', rawData.index, rawData.value)
    },
    updateExGra (state, rawData) {
      examAndCommit(state, 'graduateId', rawData.index, rawData.value)
    },
    // #endregion
    getCourse (state, rawData) {
      state.courses = rawData.content
    },
    getClassList (state, rawData) {
      state.classList = rawData.content
    },
    addCurrentCourse (state, rawData) {
      state.currentCourse = rawData.content
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
      state.graduationList = rawData.content
    },
    addOne (state) {
      state.isAdd = true
    },
    noAddOne (state) {
      state.isAdd = false
    },
    getUsualGrade (state, rawData) {
      state.stuGrade = rawData.content
    },
    removeThisWay (state, rawData) {
      const len = state.currentCourse.targetList.length
      for (let i = 0; i < len; i++) {
        if ((state.currentCourse.targetList[i] as targetArray).id === rawData) {
          state.currentCourse.targetList.splice(i, 1)
          break
        }
      }
    },
    login (state, rawData) {
      state.user = { ...rawData, isLogin: true }
      localStorage.setItem('token', rawData.token)
      axios.defaults.headers.common.Authorization = rawData.token
    },
    notFirstLogin (state) {
      state.user.isFirst = false
    }
  },
  actions: {
    getCourse ({ commit }) {
      getAndCommit(apis.course, 'getCourse', commit)
    },
    getClassList ({ commit }) {
      getAndCommit(apis.classlist, 'getClassList', commit)
    },
    addCurrentCourse ({ commit }, courseId) {
      getAndCommit(`${apis.getOne}/${courseId}`, 'addCurrentCourse', commit)
    },
    addGraduationList ({ commit }) {
      getAndCommit(apis.gradlist, 'addGraduationList', commit)
    },
    getUsualGrade ({ commit }, id) {
      getAndCommit(`${apis.usualGrade}/${id}`, 'getUsualGrade', commit)
    },
    login ({ commit }, info) {
      return axios.post(apis.login, info).then(res => {
        if (res.data.success) {
          commit('login', res.data.content)
        } else {
          alert('账号或密码错误')
        }
      })
    }
  }
})
