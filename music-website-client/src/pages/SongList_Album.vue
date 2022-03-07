<template>
  <div class="song-list-album">
    <div class="album-slide">
      <div class="album-img">
        <img :src="attachImageUrl(album.pic)">
      </div>
      <div class="album-info">
        <h2>简介：</h2>
        <span>
          {{album.intro}}
        </span>
      </div>
    </div>
    <div class="album-content">
      <div class="album-title">
        <p>{{album.title}}</p>
      </div>
      <div class="songs-body">
        <music-content :songlist="listOfSongs">
          <template slot="title">歌单</template>
        </music-content>
        <comment :id="songlistid" :type="1"></comment>
      </div>
    </div>
  </div>
</template>

<script>
import {http} from '@/api'
import {mapGetters} from 'vuex'
import MusicContent from '@/components/MusicContent'
import Comment from '@/components/Comment'
import mixin from '@/mixin'

export default {
  name: 'song-list-album',
  mixins: [mixin],
  components: {
    MusicContent,
    Comment
  },
  data () {
    return {
      songList: [],
      album: [],
      songlistid: ''
    }
  },
  computed: {
    ...mapGetters([
      'loginin',
      'tempList',
      'listOfSongs',
      'userid',
      'avator'
    ])
  },
  created () {
    this.songlistid = this.tempList.songlistId
    this.album = this.tempList
    this.getsonglist(this.songlistid)
  },
  methods: {
    getsonglist (id) {
      http.getsonginsonglist(id)
        .then(res => {
          this.songList = res
          this.$store.commit('setlistOfSongs', this.songList)
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
</script>

<style scoped>
@import "../assets/scss/songlist_album.scss";
</style>
