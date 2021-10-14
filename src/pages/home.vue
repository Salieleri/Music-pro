<template>
  <el-carousel :interval="3000" type="card" height="280px" class="carousel">
    <el-carousel-item v-for="(item,index) in swiperlist" :key="index">
      <img :src="item.picimage">
    </el-carousel-item>
  </el-carousel>
</template>

<script>
import {swiperlist} from '@/assets/data/swiper'

export default {
  name: 'home',
  data () {
    return {
      swiperlist
    }
  }
}
</script>

<style scoped>
@import "../assets/scss/home.scss";
</style>
