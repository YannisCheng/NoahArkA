<template>
  <div>
    <span>{{fromParent}}</span>
    <h5>
      <slot></slot>
    </h5>

    <h5>
      <slot name="slot_01"></slot>
    </h5>

    <slot name="slot_data" v-bind:data="data"></slot>

    <div>
      <h6>04：列表组件插槽样式展示：<br/>
        在"父级作用域"下的"slot模板内渲染列表的ItemView"，<br/>其中渲染的数据来自"由父级传给子级"</h6>
      <ol style="padding-inline-start: 0">
        <li style="list-style: none;" v-for="(item) in arrayData" :key="item.iType">
          <!--将'item'作为'slot'的元素的一个attribute，绑定到slot。
          供在父级作用域中'具体声明此slot时使用此数据'-->
          <slot name="itemSlot" v-bind:itemProp="item">
            <!--可有可无-->
            <!--{{item.iName}}:{{item.iType}}:{{item.iAddress}}-->
          </slot>
        </li>
      </ol>
    </div>

    <!--直接展示u_data的值-->
    {{u_data.firstName}}
  </div>
</template>

<script>
export default {
  name: 'ListIemSlot',
  data () {
    return {
      fromParent: '',
      data: ['zhangsan', 'lisi', 'wanwu', 'zhaoliu', 'tianqi', 'xiaoba']
    }
  },
  props: ['arrayData', 'u_data'],
  methods: {
    setBackground () {
      this.fromParent = ''
    },
  }
}
</script>

<style scoped>

</style>