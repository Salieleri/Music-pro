<template>
  <div class="home">
    <el-carousel :interval="3000" type="card" height="280px" class="carousel">
      <el-carousel-item v-for="(item,index) in swiperlist" :key="index">
        <img :src="item.picimage">
      </el-carousel-item>
    </el-carousel>
    <!--热门歌单-->
    <div class="section">
      <div class="section-title">歌单</div>
      <contentlist :contentlist="songList"></contentlist>
    </div>
  </div>
</template>

<script>
import {http} from '@/api'
import Contentlist from '@/components/Contentlist'
import {swiperlist} from '@/assets/data/swiper'

export default {
  name: 'home',
  components: {
    Contentlist
  },
  data () {
    return {
      swiperlist,
      songList: []
    }
  },
  created () {
    this.getSongList()
  },
  methods: {
    getSongList () {
      let mess = {
        'maxdata': 10,
        'page': 0
      }
      http.getSongList(mess)
        .then(res => {
          this.songList = res
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
</script>

<style scoped>
@import "../assets/scss/home.scss";
</style>
