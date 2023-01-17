<template>
  <div>
    <input-ref></input-ref>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import InputRef from '../InputRef.vue'
export default defineComponent({
  name: 'AddCourse',
  components: {
    InputRef
  }
})
</script>
