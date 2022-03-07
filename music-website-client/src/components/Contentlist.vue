<template>
  <div class="contentlist">
    <ul class="section-content">
      <li class="content-item" v-for="(item, index) in contentlist" :key="index">
        <div class="kuo" @click="goAlbum(item)">
          <img class="item-img" :src="attachImageUrl(item.pic)">
          <div class="mask" @click="goAlbum(item)"></div>
        </div>
        <p class="item-name">{{item.title}}</p>
      </li>
    </ul>
  </div>
</template>

<script>
import mixin from '../mixin'
export default {
  name: 'Contentlist',
  mixins: [mixin],
  props: {
    contentlist: Array
  },
  methods: {
    goAlbum (item) {
      this.$store.commit('setTempList', item)
      this.$router.push({path: `/song-list-album/${item.songlistId}`})
    }
  }
}
</script>

<style scoped>
@import '../assets/scss/contentlist.scss';
</style>
