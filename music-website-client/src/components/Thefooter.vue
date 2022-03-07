<template>
<div class="thefooter">
  <p v-for="(item,index) in footerindex" :key="index">
    {{item}}
  </p>
</div>
</template>

<script>
export default {
  name: 'Thefooter',
  data () {
    return {
      footerindex: [
        '关于 | 条款 | 联系我们',
        'Copyright © 2021 Salieri'
      ]
    }
  }
}
</script>

<style scoped>
@import "../assets/scss/footer.scss";
</style>
