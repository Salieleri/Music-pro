<template>
  <div class="content">
    <h1 class="title">
      <slot name="title"></slot>
    </h1>
    <hr>
    <ul>
      <li>
        <div class="song-item">
          <span class="item-index"></span>
          <span class="item-title">歌曲名</span>
          <span class="item-name">歌手</span>
          <span class="item-intro">专辑</span>
        </div>
      </li>
      <li v-for="(item, index) in songlist" :key="index" class="list-content">
        <div class="song-item" :class="{'is-play' : id === item.songId}" @dblclick="Play(item.songId, item.songPos, item.songPic, index, item.songName, item.songSinger, item.songLyric)">
          <span class="item-index">
            <span v-if="id !== item.songId">{{index + 1}}</span>
            <svg v-if="id === item.songId" aria-hidden="true" class="icon">
              <use :xlink:href="YINLIANG"></use>
            </svg>
          </span>
          <span class="item-title">{{item.songName}}</span>
          <span class="item-name">{{item.songSinger}}</span>
          <span class="item-intro">{{item.songIntro}}</span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import mixin from '@/mixin'
import {icon} from '@/assets/icon'
import {mapGetters} from 'vuex'
export default {
  name: 'music-Content',
  mixins: [mixin],
  props: {
    songlist: Array
  },
  data () {
    return {
      YINLIANG: icon.YINLIANG
    }
  },
  computed: {
    ...mapGetters([
      'id'
    ])
  }
}
</script>

<style scoped>
@import '../assets/scss/musiccontent.scss';
</style>
