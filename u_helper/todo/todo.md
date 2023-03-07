courseList 内部的 courseBox再使用v-for时要传入一个:class=""的属性，要求后端传入字符串自动匹配样式，因此传入结果需要包含一个字符串

在app中添加一个onbeforemounted 来进行登录用户的获取

获取全部课程失败后，应自动刷新页面

store中向state中添加数据时，增加判断是否成功的功能（去掉注释）

coursemanager 下的isAuthor 等待登录完成后进行

router的前置路由守卫，每个页面的路由先自动跳转到首页

每次请求都应携带用户工号，用于获取由他管理的班级或年级

在查看班级信息时，应调用接口来访问当前页面

finalGrade 接口使用的本地ip




[
  1: [
    {
      1
      CT 
    }, {
      2
      CT
    }, {
      3
      CT
    }
  ]
  2: [

  ]
]

stu : {
  id
  final: [
    {}, {}, {}, {}
  ]
}


html

css

js