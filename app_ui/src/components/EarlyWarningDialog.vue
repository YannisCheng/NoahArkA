<template>
  <div>
    <!-- 实时预警处理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body :closeOnClickModal="false">
      <el-form ref="form" :model="form" :rules="rules" label-width="90px">
        <el-row>
          <el-col :span="12">
<!--            <img src="../app_ui/src/assets/images/profile.jpg" style="height: 240px; width: 360px" alt="">-->
          </el-col>
          <el-col :span="12">
            <el-form-item label="摄像机名称" prop="name">
              <el-input v-model="form.name" placeholder="" readonly="readonly"/>
            </el-form-item>
            <el-form-item label="事件类型" prop="event">
              <el-input v-model="form.event" placeholder="" readonly="readonly"/>
            </el-form-item>
            <el-form-item label="预警时间" prop="date">
              <el-input v-model="form.date" placeholder="" readonly="readonly"/>
            </el-form-item>
            <el-form-item label="状态" prop="status">
              <el-radio-group v-model="form.status">
                <el-radio label="1" border>已确认</el-radio>
                <el-radio label="0" border>误预警</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="确认备注">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入确认备注"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click.native="submitForm">确 定</el-button>
        <el-button @click.native="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "EarlyWarningDialog",
  data() {
    return {
      rules: {
        status: [
          {required: true, message: '请选择状态', trigger: 'change'}
        ]
      },
    }
  },
  props:[
    "open","title","form"
  ],
  methods:{
    /** 提交按钮 */
    submitForm() {
      this.$emit("submitFormParent");
    },
    // 取消按钮
    cancel() {
      this.$emit("cancelParent");
      // this.open = false;
      // this.reset();
    },
  }
}
</script>

<style scoped>

</style>
