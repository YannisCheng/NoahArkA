<template>
  <div class="single_div">
    <ol style="padding-inline-start: 0">
      <li :key="value.pid" style="list-style: none;height: 40px;" v-for="(value, name, index) of singleData">
        <div class="single_div_li">
          <!--遍历对象：对象的每一个元素都是一条单独的数据展示-->
          <span style="color: #000000;">{{ index }}. {{ name }}: {{ value }}</span>
        </div>
      </li>
    </ol>

  </div>
</template>

<script>
export default {
  name: 'ShowSingleObject',
  data () {
    return {}
  },
  props: ['singleData']
}
</script>

<style scoped>
  .single_div {
    margin-top: 0;
    background: antiquewhite;
    width: 70%;
  }

  .single_div_li {
    height: 20px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
  }
</style>