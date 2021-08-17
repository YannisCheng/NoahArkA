<template>
  <div class="bg"   id="to_ximalaya">
    <div @click="toMainIndex" class="bg" id="div_one" style="color: black">去主页</div>
    <div class="div_basic" id="div_two" style="color: black" @click="openDetails"> 暂无</div>
    <EarlyWarningDialog :open="open" :title="title" :form="form" @submitFormParent="submitFormParent" @cancelParent="cancelParent" />
  </div>
</template>

<script>

import EarlyWarningDialog from "@/components/EarlyWarningDialog"

export default {
  components: {EarlyWarningDialog},
  name: 'index',
  data () {
    return {
      // 预警信息弹窗-是否显示弹出
      open: false,
      // 预警信息弹窗-弹出title标题
      title: "",
      // 预警信息弹窗-表单字段
      form: {
        name: '',
        event: '',
        status: '0',
        date: '',
        remark: ''
      },
    }
  },
  methods: {
    toMainIndex () {
      this.$router.push({ name: 'main' })
    },
    // 实时预警list点击事件-dialog表单数据初始化
    openDetails(){
      console.log("点击事件row数据为 status ：1")
      this.open = true;
      this.title = "预警信息";
      this.form.name = "name";
      this.form.event = "事件";
      this.form.status = "1";
      this.form.date = "2021-08-17 21:28:40";
    },
    // 实时预警list点击事件-提交按钮
    submitFormParent() {
      console.log('show 确定'+ this.form.name + this.form.status+this.form.event+this.form.remark);
      this.open = false;
      this.reset();
    },
    // 实时预警list点击事件-取消按钮
    cancelParent() {
      this.open = false;
      this.reset();
    },
    // 实时预警lsit点击事件-表单数据重置
    reset() {
      this.form = {
        name: undefined,
        event: undefined,
        status: '0',
        date: undefined,
        remark: undefined
      };
    },
  }
}
</script>

<style lang="scss" scoped>
  .div_basic {
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 20px;
    width: 100%;
    border: 2px;
    height: 70px;
    background: $main-color;
  }
</style>