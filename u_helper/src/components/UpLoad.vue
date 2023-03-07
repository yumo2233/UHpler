<template>
  <teleport to='#model'>
    <div class="outer">
      <div class="inner">
    <el-upload ref="upload" class="upload-demo"
        :action="website"
        :limit="1" :on-exceed="handleExceed" :auto-upload="false">
        <template #trigger>
          <el-button type="primary">选择文件</el-button>
        </template>
        <el-button type="primary" @click="submitUpload">上传</el-button>
        <el-button type="primary" @click="closeDia">返回</el-button>
        <template #tip>
          <div class="el-upload__tip text-red">
            仅限上传一个文件，文件大小小于1MB
          </div>
       </template>
      </el-upload>
  </div>
    </div>
  </teleport>
</template>

<script lang="ts">
import { ref, defineComponent } from 'vue'
import { genFileId } from 'element-plus'
import type { UploadInstance, UploadProps, UploadRawFile } from 'element-plus'
export default defineComponent({
  name: 'UpLoad',
  emits: {
    'close-model': null
  },
  props: {
    website: String
  },
  setup (props, content) {
    const upload = ref<UploadInstance>()
    const handleExceed: UploadProps['onExceed'] = (files) => {
      upload.value?.clearFiles()
      const file = files[0] as UploadRawFile
      file.uid = genFileId()
      upload.value?.handleStart(file)
    }
    const submitUpload = () => {
      upload.value?.submit()
    }
    const closeDia = () => {
      content.emit('close-model')
    }
    return {
      handleExceed, submitUpload, closeDia, upload
    }
  }
})
</script>

<style scoped>
.outer {
  background-color: rgba(0, 0, 0, .3);
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  z-index: 100;
  line-height: 70px;
  user-select: none;
}
.inner {
  background-color: #bfa;
  width: 800px;
  margin: 0 auto;
  margin-top: 300px;
  border-radius: 10px;
}
</style>
