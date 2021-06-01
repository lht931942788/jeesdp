<template>
  <el-dialog v-model="centerDialogVisible" destroy-on-close title="提示" width="30%">
    <template #title>
      <span>
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
      </span>
      <span style="float: right;position: relative;top: 0;right: 20px">
        <el-button icon="el-icon-umbrella" type="text"></el-button>
      </span>
    </template>
    <span>需要注意在 Dialog 打开前是这条内容和下面的内容都是不会被渲染的</span>
    <strong>额外的内容</strong>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script>
export default {
  data() {
    return {
      centerDialogVisible: true
    };
  }
};
</script>
