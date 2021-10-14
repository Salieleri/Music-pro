<template>
  <div class="the-header">
    <ul class="navbar">
      <li @click="gohome">首页</li>
      <li>歌曲</li>
      <li>歌单</li>
      <li>我的收藏</li>
      <li>
        <div class="header-search">
          <input type="text" placeholder="搜索音乐" @keyup.enter="argos" v-model="keywords">
        </div>
      </li>
      <li>
        <div>
          <img class="icon" src="../assets/img/SOUSUO.jpg" aria-hidden="true">
        </div>
      </li>
      <li>登录</li>
      <li>注册</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'Theheader',
  data () {
    return {
      keywords: ''
    }
  },
  methods: {
    gohome () {
      this.$router.push({path: '/'})
    }
  }
}
</script>

<style scoped>
@import "../assets/scss/header.scss";
</style>
