<template>
  <input @input="updateValue" @blur="validateInput" :value="inputRef.val" :placeholder="placeholder" :type="type"/>
</template>

<script lang="ts">
import { defineComponent, reactive } from 'vue'
// import isValidateType from '@/hooks/useRules'
export default defineComponent({
  name: 'InputRef',
  props: {
    modelValue: [String, Number],
    placeholder: String,
    type: String,
    maxLength: {
      type: Number,
      default: 20
    },
    maxValue: {
      type: Number,
      default: 1
    }
  },
  setup (props, context) {
    const inputRef = reactive({
      val: props.modelValue || ''
    })
    const updateValue = (e: Event) => {
      const targetValue = (e.target as HTMLInputElement).value
      inputRef.val = targetValue
      context.emit('update:modelValue', targetValue)
    }
    const validateInput = () => {
      if ((inputRef.val as string).length > props.maxLength) {
        // 错误处理
      }
    }
    return {
      inputRef,
      updateValue,
      validateInput
    }
  }
})
</script>

<style scoped>
input {
  width: 350px;
  height: 30px;
  outline: none;
  border-radius: 6px;
  border: 1px #DCDFE6 solid;
  padding-left: 10px;
}
input:focus{
  border: #409EFF 1px solid;
}
</style>
