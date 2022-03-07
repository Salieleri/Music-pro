<template>
  <div class="setting">
    <div class="leftCol">
      <div class="settingsMainHeader">设置</div>
      <ul class="setting-aside">
        <li v-for="(item, index) in settingList" :key="index" :class="{activeColor: item.name === activeName}" @click="handleClick(item)">
          {{item.name}}
        </li>
      </ul>
    </div>
    <div class="contentCol">
      <component :is="componentSrc"></component>
    </div>
  </div>
</template>

<script>
import info from '@/components/info'
import upload from '@/components/upload'
export default {
  name: 'setting',
  components: {
    info,
    upload
  },
  data () {
    return {
      activeName: '个人信息',
      componentSrc: 'info',
      settingList: [{
        name: '个人信息',
        path: 'info'
      }, {
        name: '修改头像',
        path: 'upload'
      }]
    }
  },
  methods: {
    handleClick (item) {
      this.activeName = item.name
      this.componentSrc = item.path
    }
  }
}
</script>

<style scoped>
@import '../assets/scss/setting.scss';
</style>
