<template>
  <div class="contentlist">
    <ul>
      <li class="content-item">
        <div class="kuo" @click="">
          <img class="item-img" :src="" alt="">
        </div>
        <div class="mask" @click=""></div>
        <p class="item-name"></p>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'Contentlist'
}
</script>

<style scoped>
@import '../assets/scss/contentlist.scss';
</style>
