<template>
  <div class="left_div">
    <ol style="padding-inline-start: 0">
      <!--list-style: none 不显示左侧小点-->
      <li style="list-style: none;" v-for="(item,index) of datas" :key="index" >
        <div class="left_div_li">
          <span style="color: black">{{item.appliancesName}}</span>
        </div>
      </li>
    </ol>

  </div>
</template>

<script>
export default {
  name: 'AllItems',
  data () {
    return {}
  },
  props: ['datas']
}
</script>

<style scoped>
  .left_div {
    margin-top: 0;
    background: darkorange;
    width: 30%;
  }

  .left_div_li {
    margin-top: 0;
    background: #42b983;
    height: 40px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
</style>