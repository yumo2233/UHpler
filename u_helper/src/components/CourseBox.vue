<template>
  <div class="container">
    <div v-if="isFirst">
      <span class="plus">+</span> <br>
      <span class="addCourse">新增课程</span>
    </div>
    <div v-else style="width: 100%;height: 100%;" @click="goCourseDetail">
      <div class="left_color_box" :style="{backgroundColor: color}"></div>
      <span class="academy" :style="{backgroundColor: color}">电子工程学院</span>
      <span v-if="isAuthor" @click.stop="goEdit"><el-icon><Edit /></el-icon></span>
      <span v-else @click.stop="goView"><el-icon><View /></el-icon></span>
      <span class="course">通信原理(TX001)</span>
      <span class="teacher">张老师</span>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
export default defineComponent({
  name: 'CourseBox',
  props: {
    color: {
      type: String,
      default: '#337fe7'
    },
    isFirst: {
      type: Boolean,
      default: false
    }
  },
  setup () {
    const goCourseDetail = () => {
      // 跳转路由
      console.log(222)
    }
    const isAuthor = ref(true)
    const goEdit = () => {
      console.log(777)
    }
    const goView = () => {
      console.log(888)
    }
    return {
      goCourseDetail,
      isAuthor,
      goEdit,
      goView
    }
  }
})
</script>

<style scoped>
.container {
  margin-top: 20px;
  width: 250px;
  height: 150px;
  background-color: white;
  border-radius: 15px;
  position: relative;
  user-select: none;
}
.left_color_box {
  height: 100%;
  width: 12px;
  float: left;
  overflow: hidden;
  border-radius: 15px 0 0 15px;
  background-color: #a07be6;
}
.academy {
  padding: 13px 16px;
  text-align: center;
  position: absolute;
  left: 30px;
  top: 20px;
  border-radius: 10px;
  opacity: .8;
  font-weight: 200;
  font-size: 12px;
}
.course {
  position: absolute;
  left: 30px;
  top: 75px;
  font-size: 20px;
  font-weight: bold;
}
.teacher {
  position: absolute;
  left: 30px;
  bottom: 16px;
  font-size: 16px;
  color: rgba(0, 0, 0, .4);
}
.plus {
  position: absolute;
  text-align: center;
  font-size: 50px;
  font-weight: bolder;
  color: grey;
  top: 25px;
  left: 100px;
}
.addCourse {
  position: absolute;
  text-align: center;
  left: 90px;
  top: 90px;
}
i {
  width: 30px;
  height: 30px;
  background-color: #f5f6f7;
  border-radius: 50%;
  position: absolute;
  top: 24px;
  right: 25px;
}
</style>
