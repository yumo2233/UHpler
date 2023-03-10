export const apis = {
  course: '/course/listAllCourse',
  modfiy: '/course/modifyOne',
  addone: '/course/addOne',
  classlist: '/course/listAllClass',
  gradlist: '/course/listAllGraduate',
  getOne: '/course/listOne',
  usualGrade: '/course/listAllStudent',
  login: '/user/login',
  change: '/user/updatePassword',
  listCollege: 'course/CollegeAndGrade',
  modefyUsual: '/course/modifyAllStudent',
  modefyChecklist: '/course/modifyUsual',
  download: '/course/downloadUsual', // excel
  gradContent: '/course/listFinal', // 期末成绩构成
  getClassAndPro: '/graduate/selectGAP',
  collegeAndGrade: '/graduate/selectCollegeAndGrade',
  graduatePage: '/graduate/selectGraduatePage',
  deleteTarget: '/graduate/deleteTarget',
  deleteGraduateInfo: '/graduate/deleteGraduateInfo',
  updateTarget: '/graduate/updateTarget',
  insertTarget: '/graduate/insertTarget',
  updateGraduationName: '/graduate/updateGraduationName',
  insertInfoTarget: '/graduate/insertInfoTarget',
  selectClass: '/class/selectClass',
  selectStu: '/class/selectStudent',
  updateClass: '/class/updateClass',
  updateSingleStudent: '/class/updateSingleStudent',
  insetSingleStudent: '/class/insetSingleStudent',
  deleteSingleStudent: '/class/deleteSingleStudent',
  deleteClassClass: '/class/deleteClassClass',
  downloadReport: '/course/downloadReport', // 下载课程报告
  listFinalStructure: '/course/listFinalStructure', // 期末成绩管理题目编辑使用
  modifyFinalStructure: '/course/modifyFinalStructure', // 修改期末题目
  downloadFinal: 'course/downloadFinal', // 下载期末成绩表格
  uploadFinalScore: '/course/uploadFinalScore', // 上传期末成绩
  listSemester: '/course/listSemester',
  listPre: '/course/listPre',
  insetClass: '/class/insertCASBatch',
  addclassWeb: 'localhost:8080', // 上传文件新增班级成员
  uploadFinalGrade: 'localhost:8080'// 上传期末成绩
}
