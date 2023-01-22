<template>
  <filter-bar></filter-bar>
  <class-detail></class-detail>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import FilterBar from '../components/aboutClass/FilterBar.vue'
import ClassDetail from './aboutClass/ClassDetail.vue'
export default defineComponent({
  name: 'ClassManage',
  components: {
    FilterBar,
    ClassDetail
  }
})
</script>
