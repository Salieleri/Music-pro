<template>
<div>
  <Theheader/>
  <router-view/>
  <Thefooter/>
</div>
</template>

<script>

import Theheader from '@/components/the-header'
import Thefooter from '@/components/thefooter'
export default {
  name: 'Main',
  components: {
    Theheader,
    Thefooter
  }
}
</script>

<style scoped>

</style>
