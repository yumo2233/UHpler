<template>
  <div class="home">
    <div ref="file"></div>
  </div>
</template>

<script lang="ts">
import axios from 'axios'
import { apis } from '@/common/apis'
import { renderAsync } from 'docx-preview'
import { defineComponent, ref, onBeforeMount, computed } from 'vue'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
export default defineComponent({
  name: 'PersonalReport',
  setup () {
    const store = useStore<GlobalDataProps>()
    const info = computed(() => store.state.currentCourse)
    // // eslint-disable-next-line @typescript-eslint/no-var-requires
    // const docx = require('docx-preview')
    const file = ref()
    console.log(file)
    onBeforeMount(() => {
      axios.get(`${apis.downloadReport}/${info.value.id}`, { responseType: 'blob' }).then(res => {
        renderAsync(res.data, file.value)
      })
    })
    return {
      file
    }
  }
})
</script>
