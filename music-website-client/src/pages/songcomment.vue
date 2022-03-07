<template>
  <div class="comment">
    <Comment :id="songid" :type="type"></Comment>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import Comment from '@/components/Comment'
export default {
  name: 'songcomment',
  components: {
    Comment
  },
  computed: {
    ...mapGetters([
      'listOfSongs',
      'listIndex'
    ])
  },
  data () {
    return {
      songid: '',
      type: 0
    }
  },
  mounted () {
    this.songid = this.listOfSongs[this.listIndex].songId
  }
}
</script>

<style scoped>
@import '../assets/scss/songcomment.scss';
</style>
