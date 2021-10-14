<template>
  <el-row>
    <el-col :span="2"><div class="grid-content"></div></el-col>
    <el-col :span="20">
      <el-footer>
        <p v-for="(item,index) in footerindex" :key="index">
          {{item}}
        </p>
      </el-footer>
    </el-col>
    <el-col :span="2"><div class="grid-content"></div></el-col>
  </el-row>
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
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
