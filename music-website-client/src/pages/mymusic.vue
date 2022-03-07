<template>
  <div class="my-music">
    <div class="coll-slide">
      <div class="avatorimage">
        <img :src="attachImageUrl(avator)" alt="">
      </div>
      <ul class="album-info">
        <li>昵称:{{userid}}</li>
<!--        之后会加个人信息-->
      </ul>
    </div>
    <div>
<!--      <div></div> 之后会加个人信息-->
      <div class="album-content">
        <div class="songs-body">
          <music-content :songlist="collectlist">
            <template slot="title">我的收藏</template>
          </music-content>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import mixin from '@/mixin'
import {mapGetters} from 'vuex'
import {http} from '@/api'
import MusicContent from '@/components/MusicContent'
export default {
  name: 'mymusic',
  components: {
    MusicContent
  },
  mixins: [mixin],
  data () {
    return {
      collectlist: []
    }
  },
  computed: {
    ...mapGetters([
      'userid',
      'id',
      'listOfSongs',
      'avator'
    ])
  },
  mounted () {
    this.getcollection(this.userid)
  },
  methods: {
    getcollection (id) {
      http.getcollection(id)
        .then(res => {
          this.collectlist = res
          this.$store.commit('setlistOfSongs', res)
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
</script>

<style scoped>
@import "../assets/scss/mymusic.scss";
</style>
