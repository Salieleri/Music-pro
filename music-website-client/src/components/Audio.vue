<template>
  <div>
    <audio :src="url" controls="controls" ref="player" @canplay="startPlay" @timeupdate="timeupdate" @ended="ended"></audio>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
export default {
  name: 'SongAudio',
  computed: {
    ...mapGetters([
      'id',
      'url',
      'listOfSongs',
      'isPlay',
      'volume',
      'curTime',
      'changeTime',
      'autoNext'
    ])
  },
  methods: {
    toggle () {
      setTimeout(() => {}, 500)
      let player = this.$refs.player
      if (this.isPlay) {
        player.play()
      } else {
        player.pause()
      }
    },
    startPlay () {
      let player = this.$refs.player
      this.$store.commit('setDuration', player.duration)
      player.play()
      this.$store.commit('setIsPlay', true)
    },
    timeupdate () {
      let player = this.$refs.player
      this.$store.commit('setCurTime', player.currentTime)
    },
    ended () {
      this.$store.commit('setIsPlay', false)
      this.$store.commit('setCurTime', 0)
      this.$store.commit('setAutoNext', !this.autoNext)
    }
  },
  watch: {
    isPlay () {
      this.toggle()
    },
    volume (val) {
      this.$refs.player.volume = val
    },
    changeTime () {
      let player = this.$refs.player
      player.currentTime = this.changeTime
    }
  }
}
</script>

<style scoped>
audio{
  display:none;
}
</style>
