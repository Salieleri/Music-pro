<template>
<div>
  <SongAudio/>
  <Theheader/>
  <the-aside></the-aside>
  <router-view/>
  <playbar/>
  <Thefooter/>
</div>
</template>

<script>

import SongAudio from '@/components/Audio'
import Theheader from '@/components/the-header'
import Thefooter from '@/components/thefooter'
import playbar from '@/components/playbar'
import Contentlist from '@/components/Contentlist'
import TheAside from '@/components/theAside'
export default {
  name: 'Main',
  components: {
    TheAside,
    Theheader,
    Thefooter,
    playbar,
    Contentlist,
    SongAudio
  }
}
</script>

<style scoped>

</style>
