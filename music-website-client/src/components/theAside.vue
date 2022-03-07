<template>
  <transition name="slide-fade">
    <div class="the-aside" v-if="showAside">
      <h2 class="title">播放列表</h2>
      <ul class="menus">
        <li v-for="(item, index) in listOfSongs" :key="index" :class="{'is-play': id === item.songId}" @click="Play(item.songId, item.songPos, item.songPic, index, item.songName, item.songSinger, item.songLyric)">
          {{item.songName}}
        </li>
      </ul>
    </div>
  </transition>
</template>

<script>
import {mapGetters} from 'vuex'
import mixin from '../mixin/index'

export default {
  name: 'theAside',
  mixins: [mixin],
  computed: {
    ...mapGetters([
      'showAside',
      'listOfSongs',
      'id'
    ])
  },
  mounted () {
    let _this = this
    document.addEventListener('click', () => {
      _this.$store.commit('setshowAside', false)
    }, true)
  }
}
</script>

<style scoped>
@import '../assets/scss/theaside.scss';
</style>
