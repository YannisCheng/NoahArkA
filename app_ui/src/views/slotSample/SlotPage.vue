<template>
  <div style="width: 100%">
    <el-button @click="changeChildView">点击在子Vue组件中显示数据</el-button>
    <!--展示 'ListIemSlot' 组件内部各种slot-->
    <ListIemSlot ref="listVue" v-bind:array-data="arrayData" v-bind:u_data="user">

      <!--下面这种写法是'<template v-slot:default>'的简写-->
      <template>
        01: 展示名为"default"的slot内容
      </template>

      <template v-slot:slot_01>
        <span style="background: #42b983;color: white">
          02: 展示名为"slot_01"的slot内容
        </span>
      </template>

      <template v-slot:slot_data="dataFromChild">
        <h5>
          03：展示名为"slot_data"的slot内容,数据来自子组件
        </h5>
        <div style="background: darkorange;color: white;height: 40px">
          {{dataFromChild.data}}
        </div>
      </template>

      <!--
      绑定在slot上的attribute称为'slot prop',
      在父级作用域中用带'值'的v-slot来定义我们提供的'slot prop'名字。
      在这里将包含所有插槽 prop 的对象命名为'itemP'
      -->
      <template v-slot:itemSlot="itemP">
        <div style="background: lightgrey;height: 30px;margin-top: 10px;">
          {{itemP.itemProp.iType}}:{{itemP.itemProp.iName}}:{{itemP.itemProp.iAddress}}
        </div>
      </template>
    </ListIemSlot>
  </div>
</template>

<script>
import ListIemSlot from '@/views/slotSample/ListIemSlot'

export default {
  name: 'album_page',
  components: { ListIemSlot },
  data () {
    return {
      user: {
        firstName: '11111',
        lastName: 'aaaaa'
      },
      arrayData: [
        { iName: 'name_01', iType: '1', iAddress: 'Add_01' },
        { iName: 'name_02', iType: '2', iAddress: 'Add_02' },
        { iName: 'name_03', iType: '3', iAddress: 'Add_03' },
        { iName: 'name_04', iType: '4', iAddress: 'Add_04' },
        { iName: 'name_05', iType: '5', iAddress: 'Add_05' },
        { iName: 'name_06', iType: '6', iAddress: 'Add_06' },
        { iName: 'name_07', iType: '7', iAddress: 'Add_07' },
        { iName: 'name_08', iType: '8', iAddress: 'Add_08' },
        { iName: 'name_09', iType: '9', iAddress: 'Add_09' },
        { iName: 'name_10', iType: '10', iAddress: 'Add_10' },
      ],
    }
  },
  methods: {
    changeChildView () {
      this.$refs.listVue.fromParent = '父组通过自身的点击事件修改了子组件的变量数值'
    },
  }
}
</script>

<style scoped>

</style>