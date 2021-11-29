<template>
  <div class="the-header">
    <ul class="navbar">
      <li :class="{active: item.name === activeName}" v-for="item in Menumsg" :key="item.path" @click="gopage(item.path, item.name)">
        {{item.name}}
      </li>
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
      <li :class="{active: item.name === activeName}" v-for="item in Loginmsg" :key="item.path" @click="gopage(item.path, item.name)">
        {{item.name}}
      </li>
    </ul>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import {Loginmsg, Menumsg, Usermsg} from '@/assets/data/header'
import {icon} from '@/assets/icon'

export default {
  name: 'Theheader',
  mounted () {
    // document.querySelector('#user').addEventListener('click', function (e) {
    //   document.querySelector('.menu').classList.add('show')
    //   e.stopPropagation()// 关键在于阻止冒泡
    // }, false)
    // // 点击“菜单”内部时，阻止事件冒泡。(这样点击内部时，菜单不会关闭)
    // document.querySelector('.menu').addEventListener('click', function (e) {
    //   e.stopPropagation()
    // }, false)
    // document.addEventListener('click', function () {
    //   document.querySelector('.mesdsdsdsdnu').classList.remove('show')
    // }, false)
  },
  data () {
    return {
      keywords: '',
      Loginmsg,
      Menumsg,
      Usermsg,
      SOUSUO: icon.SOUSUO
    }
  },
  methods: {
    gohome () {
      this.$router.push({path: '/'})
    },
    gopage (path, value) {
      this.changeindex(value)
      this.$router.push({path: path})
    },
    changeindex (value) {
      this.$store.commit('setactiveName', value)
    }
  },
  computed: {
    ...mapGetters([
      'activeName',
      'loginin'
    ])
  }
}
</script>

<style scoped>
@import "../assets/scss/header.scss";
</style>
