<template>
  <div class="common-layout" id="app">
    <el-container style="height: 100%;">
      <el-header>
        <global-header></global-header>
      </el-header>
      <el-container style="height: 100%;">
        <el-aside width="225px">
          <aside-bar></aside-bar>
        </el-aside>
        <el-main style="background-color: #f5f6f7;">
          <main-box></main-box>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
// 组件
import GlobalHeader from './components/GlobalHeader.vue'
import AsideBar from './components/AsideBar.vue'
import MainBox from './components/MainBox.vue'
export default defineComponent({
  name: 'App',
  components: {
    GlobalHeader,
    AsideBar,
    MainBox
  }
})
</script>

<style>
#app{
  height: 100%;
}
a {
  text-decoration: none;
}
</style>
