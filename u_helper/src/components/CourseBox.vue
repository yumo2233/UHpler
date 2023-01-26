<template>
  <div class="container">
    <template v-if="isFirst">
        <div style="width: 100%;height: 100%;">
        <span class="plus">+</span> <br>
        <span class="addCourse">新增课程</span>
      </div>
    </template>
    <template v-else>
      <div style="width: 100%;height: 100%;">
        <div class="left_color_box"></div>
        <span class="academy">{{ courses && courses.college }}</span>
        <span v-if="isAuthor"><el-icon><Edit /></el-icon></span>
        <span v-else><el-icon><View /></el-icon></span>
        <span class="course">{{ courses && `${courses.name} (${courses.id})` }}</span>
        <span class="teacher">{{ courses && courses.teacher }}</span>
        <span>
          <el-dropdown v-if="isAuthor" class="more">
            <span class="el-dropdown-link">
              <el-icon><MoreFilled /></el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="goCopy">copy</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </span>
      </div>
    </template>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, PropType } from 'vue'
import { ICourses, GlobalDataProps } from '@/store/index'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default defineComponent({
  name: 'CourseBox',
  props: {
    isFirst: {
      type: Boolean,
      default: false
    },
    courses: {
      type: Object as PropType<ICourses>
    }
  },
  setup (props) {
    const store = useStore<GlobalDataProps>()
    const router = useRouter()
    const isAuthor = ref(true)
    const goCopy = () => {
      store.commit('addOne')
      router.push({ path: '/course', query: { id: props.courses?.id } })
    }
    return {
      isAuthor,
      goCopy
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
}
.academy {
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
  top: 60px;
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
  top: 20px;
  right: 25px;
}
.more {
  position: absolute;
  bottom: 60px;
  right: 0px;
}
.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}
</style>
