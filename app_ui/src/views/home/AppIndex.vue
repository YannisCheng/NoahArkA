<template>
  <div class="bg"   id="to_ximalaya">
    <div @click="toMainIndex" class="bg" id="div_one" style="color: black">去主页</div>
    <div class="div_basic" id="div_two" style="color: black"> 暂无</div>
  </div>
</template>

<script>
export default {
  name: 'index',
  data () {
    return {}
  },
  methods: {
    toMainIndex () {
      this.$router.push({ name: 'main' })
    }
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