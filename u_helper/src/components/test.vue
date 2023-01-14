<!-- eslint-disable vue/multi-word-component-names -->
<!-- MyComponent.vue -->
<template>
  <input
    type="text"
    :value="title"
    @input="sub"
  />
</template>

<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'
defineProps(['title'])
defineEmits(['update:title'])
const sub = (e: Event) => {
  console.log(e)
}
</script>
