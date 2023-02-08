courseList 内部的 courseBox再使用v-for时要传入一个:class=""的属性，要求后端传入字符串自动匹配样式，因此传入结果需要包含一个字符串

在app中添加一个onbeforemounted 来进行登录用户的获取

获取全部课程失败后，应自动刷新页面

store中向state中添加数据时，增加判断是否成功的功能（去掉注释）

coursemanager 下的isAuthor 等待登录完成后进行

router的前置路由守卫，每个页面的路由先自动跳转到首页

const options = {
  学院: string
  专业: string
  年级: string | number
  毕业要求: [
    {
      name: string
      指标点: [
        {
          编号: string
          内容: string
        }, {
          编号: string
          内容: string
        }
      ]
    }, {
      name: string
      指标点: [
        {
          编号: string
          内容: string
        }, , {
          编号: string
          内容: string
        }
      ]
    }
  ]
}

{
    "success": false,
    "message": "sint",
    "content": [
        {
            "毕业要求": [
                {
                    "graduateName": "次结且正斯",
                    "graduateId": 36,
                    "graduateTargetInfo": [
                        {
                            "name": "响们个步件",
                            "id": 2,
                            "content": "cupidatat Lorem incididunt ut"
                        },
                        {
                            "name": "海向许机量",
                            "id": 5,
                            "content": "minim amet culpa fugiat"
                        },
                        {
                            "name": "少要没因族",
                            "id": 35,
                            "content": "consectetur amet"
                        },
                        {
                            "name": "率被克都志称",
                            "id": 70,
                            "content": "dolore"
                        },
                        {
                            "name": "识手候理建",
                            "id": 59,
                            "content": "sit enim"
                        }
                    ]
                },
                {
                    "graduateName": "府内由命查",
                    "graduateId": 53,
                    "graduateTargetInfo": [
                        {
                            "name": "斯区日线保",
                            "id": 25,
                            "content": "ut non"
                        }
                    ]
                },
                {
                    "graduateName": "素路深来数",
                    "graduateId": 35,
                    "graduateTargetInfo": [
                        {
                            "name": "打常其记",
                            "id": 42,
                            "content": "exercitation"
                        },
                        {
                            "name": "活年各车万",
                            "id": 13,
                            "content": "deserunt Lorem cillum et in"
                        }
                    ]
                },
                {
                    "graduateName": "走理知农为单电",
                    "graduateId": 3,
                    "graduateTargetInfo": [
                        {
                            "name": "布许各工资力",
                            "id": 3,
                            "content": "ad"
                        },
                        {
                            "name": "图众最切",
                            "id": 10,
                            "content": "in"
                        },
                        {
                            "name": "关干最将很再",
                            "id": 45,
                            "content": "dolor adipisicing"
                        },
                        {
                            "name": "认为音",
                            "id": 9,
                            "content": "irure ea"
                        }
                    ]
                }
            ],
            "professional": "veniam",
            "college": "id anim ipsum dolore",
            "grade": 66
        },
        {
            "college": "magna elit Ut quis eu",
            "grade": 92,
            "毕业要求": [
                {
                    "graduateName": "取温派置",
                    "graduateId": 19,
                    "graduateTargetInfo": [
                        {
                            "name": "级时阶农",
                            "id": 80,
                            "content": "Ut nulla labore ullamco"
                        },
                        {
                            "name": "温百国说选明",
                            "id": 79,
                            "content": "sunt sint"
                        },
                        {
                            "name": "改农果直片",
                            "id": 47,
                            "content": "quis Excepteur laboris et"
                        },
                        {
                            "name": "义儿车",
                            "id": 34,
                            "content": "voluptate mollit consequat"
                        }
                    ]
                },
                {
                    "graduateName": "该工不们",
                    "graduateId": 99,
                    "graduateTargetInfo": [
                        {
                            "name": "进况备",
                            "id": 86,
                            "content": "Lorem amet id"
                        },
                        {
                            "name": "精响出真",
                            "id": 58,
                            "content": "ad mollit sint"
                        },
                        {
                            "name": "圆标革周火命",
                            "id": 38,
                            "content": "laboris ad commodo"
                        }
                    ]
                },
                {
                    "graduateName": "思情院周",
                    "graduateId": 12,
                    "graduateTargetInfo": [
                        {
                            "name": "象头战年期",
                            "id": 4,
                            "content": "amet ullamco in minim deserunt"
                        },
                        {
                            "name": "理质计车",
                            "id": 74,
                            "content": "sunt culpa mollit"
                        },
                        {
                            "name": "量候所单",
                            "id": 90,
                            "content": "pariatur fugiat"
                        }
                    ]
                },
                {
                    "graduateName": "克争而",
                    "graduateId": 6,
                    "graduateTargetInfo": [
                        {
                            "name": "象热广",
                            "id": 61,
                            "content": "quis"
                        }
                    ]
                }
            ],
            "professional": "nisi ut id"
        }
    ]
}