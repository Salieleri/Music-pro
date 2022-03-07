<template>
  <div class="search">
    <nav class="searchList-nav" ref="change">
      <span :class="{isActive: toggle === 'Songs'}" @click="ChangeView('Songs')">歌曲</span>
      <span :class="{isActive: toggle === 'SongList'}" @click="ChangeView('SongList')">歌单</span>
    </nav>
    <component :is="currentView"></component>
  </div>
</template>

<script>
import mixin from '@/mixin'
import {mapGetters} from 'vuex'
import SearchSongs from '@/components/search/SearchSong'
import SearchSongList from '@/components/search/SearchSongList'
export default {
  name: 'Search',
  mixins: [mixin],
  components: {
    SearchSongs,
    SearchSongList
  },
  data () {
    return {
      currentView: 'SearchSongs',
      toggle: 'Songs'
    }
  },
  computed: {
    ...mapGetters([
      'searchwords'
    ])
  },
  watch: {
    searchwords () {
      this.getsong()
    }
  },
  methods: {
    ChangeView (component) {
      this.currentView = 'search' + component
      this.toggle = component
    }
  },
  mounted () {
    this.getsong()
  }
}
</script>

<style scoped>
@import '../assets/scss/Search.scss';
</style>
