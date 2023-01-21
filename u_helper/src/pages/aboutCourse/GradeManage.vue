<template>
  <div class="outer">
    <el-dialog v-model="centerDialogVisible" title="Warning" width="30%" center>
      <span>
        {{ message }}
      </span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="centerDialogVisible = false">Cancel</el-button>
          <el-button type="primary" @click="centerDialogVisible = false">
            Confirm
          </el-button>
        </span>
      </template>
    </el-dialog>
    <div>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="date" label="Date" width="150" />
        <el-table-column label="Delivery Info">
        <el-table-column prop="name" label="Name" width="120" />
        <el-table-column label="Address Info">
        <el-table-column prop="state" label="State" width="120" />
        <el-table-column prop="city" label="City" width="120" />
        <el-table-column prop="address" label="Address" />
        <el-table-column prop="zip" label="Zip" width="120" />
        </el-table-column>
        </el-table-column>
      </el-table>
    </div>
    <drawer-vue></drawer-vue>
    <div style="margin-bottom: 0;">
      <el-button type="primary" :disabled="isAuthor" @click="handleDownload">下载</el-button>
      <el-button type="primary" @click="handleOpen" :disabled="isAuthor">平时成绩构成</el-button>
      <el-button :disabled="isAuthor" type="primary" @click="centerDialogVisible = true">保存</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import DrawerVue, { demitter } from '@/components/DrawerVue.vue'
import { defineComponent, ref } from 'vue'
export default defineComponent({
  props: {
    isAuthor: {
      type: Boolean,
      default: true
    }
  },
  name: 'GradeManage',
  components: {
    DrawerVue
  },
  setup () {
    const message = ref('')
    const centerDialogVisible = ref(false)
    const handleOpen = () => {
      demitter.emit('on-drawer-open', () => null)
    }
    const handleDownload = () => {
      console.log('handle download')
    }
    const tableData = [
      {
        date: '2016-05-03',
        name: 'Tom',
        state: 'California',
        city: 'Los Angeles',
        address: 'No. 189, Grove St, Los Angeles',
        zip: 'CA 90036'
      },
      {
        date: '2016-05-02',
        name: 'Tom',
        state: 'California',
        city: 'Los Angeles',
        address: 'No. 189, Grove St, Los Angeles',
        zip: 'CA 90036'
      },
      {
        date: '2016-05-04',
        name: 'Tom',
        state: 'California',
        city: 'Los Angeles',
        address: 'No. 189, Grove St, Los Angeles',
        zip: 'CA 90036'
      },
      {
        date: '2016-05-01',
        name: 'Tom',
        state: 'California',
        city: 'Los Angeles',
        address: 'No. 189, Grove St, Los Angeles',
        zip: 'CA 90036'
      },
      {
        date: '2016-05-08',
        name: 'Tom',
        state: 'California',
        city: 'Los Angeles',
        address: 'No. 189, Grove St, Los Angeles',
        zip: 'CA 90036'
      },
      {
        date: '2016-05-06',
        name: 'Tom',
        state: 'California',
        city: 'Los Angeles',
        address: 'No. 189, Grove St, Los Angeles',
        zip: 'CA 90036'
      },
      {
        date: '2016-05-07',
        name: 'Tom',
        state: 'California',
        city: 'Los Angeles',
        address: 'No. 189, Grove St, Los Angeles',
        zip: 'CA 90036'
      }
    ]
    return {
      tableData,
      message,
      handleOpen,
      handleDownload,
      centerDialogVisible
    }
  }
})
</script>
