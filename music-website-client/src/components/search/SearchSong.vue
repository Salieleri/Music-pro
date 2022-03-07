<template>
  <div class="searchsongs">
    <music-content :songlist="listOfSongs"></music-content>
  </div>
</template>

<script>
import MusicContent from '../MusicContent'
import {mapGetters} from 'vuex'

export default {
  name: 'SearchSongs',
  components: {
    MusicContent
  },
  computed: {
    ...mapGetters([
      'listOfSongs'
    ])
  }
}
</script>

<style scoped>
@import '../../assets/scss/searchsongs.scss'
</style>
