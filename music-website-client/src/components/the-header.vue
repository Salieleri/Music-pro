<template>
  <div class="the-header">
    <div class="header-logo" @click="gohome">
      <svg class="icon-header" aria-hidden="true">
        <use :xlink:href="ERJI"></use>
      </svg>
      <span>{{musicName}}</span>
    </div>
    <ul class="navbar">
      <li :class="{active: item.name === activeName}" v-for="item in Menumsg" :key="item.path" @click="gopage(item.path, item.name)">
        {{item.name}}
      </li>
      <li>
        <div class="header-search">
          <input type="text" placeholder="搜索音乐" @keyup.enter="gosearch" v-model="keywords">
        </div>
      </li>
      <li>
        <svg class="icon" aria-hidden="true" @click="gosearch">
          <use :xlink:href="SOUSUO"></use>
        </svg>
      </li>
      <li v-show="!loginin" :class="{active: item.name === activeName}" v-for="item in Loginmsg" :key="item.path" @click="gopage(item.path, item.name)">
        {{item.name}}
      </li>
    </ul>
    <!--设置-->
    <div class="header-right" v-show="loginin">
      <div id="user">
        <img :src="attachImageUrl(this.avator)" alt="">
      </div>
      <ul class="menu">
        <li v-for="(item, index) in Usermsg" :key="index" @click="goMenuList(item.path)">{{item.name}}</li>
      </ul>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import {Loginmsg, Menumsg, Usermsg} from '@/assets/data/header'
import {icon} from '@/assets/icon'
import mixin from '@/mixin'

export default {
  name: 'Theheader',
  mixins: [mixin],
  mounted () {
    document.querySelector('#user').addEventListener('click', function (e) {
      document.querySelector('.menu').classList.add('show')
      e.stopPropagation()// 关键在于阻止冒泡
    }, false)
    // 点击“菜单”内部时，阻止事件冒泡。(这样点击内部时，菜单不会关闭)
    document.querySelector('.menu').addEventListener('click', function (e) {
      e.stopPropagation()
    }, false)
    document.addEventListener('click', function () {
      document.querySelector('.menu').classList.remove('show')
    }, false)
  },
  data () {
    return {
      keywords: '',
      Loginmsg,
      Menumsg,
      Usermsg,
      SOUSUO: icon.SOUSUO,
      ERJI: icon.ERJI,
      musicName: 'Music-argo'
    }
  },
  methods: {
    gohome () {
      this.$router.push({path: '/'})
    },
    gopage (path, value) {
      if (!this.loginin) {
        if (value === '我的音乐') {
          this.notify('请先登录！', 'warning')
          return
        }
      }
      this.changeindex(value)
      this.$router.push({path: path})
    },
    changeindex (value) {
      this.$store.commit('setactiveName', value)
    },
    gosearch () {
      this.$store.commit('setsearchwords', this.keywords)
      if (this.keywords) this.$router.push({path: '/search', query: {keywords: this.keywords}})
    },
    goMenuList (path) {
      document.querySelector('.menu').classList.remove('show')
      if (path) {
        this.$router.push({path: path})
      } else {
        this.$store.commit('setloginin', false)
        if (window.location.pathname === '/') {
          this.$router.go(0)
        } else {
          this.$router.push('/')
        }
      }
    }
  },
  computed: {
    ...mapGetters([
      'activeName',
      'loginin',
      'searchwords',
      'avator'
    ])
  }
}
</script>

<style scoped>
@import "../assets/scss/header.scss";
</style>
