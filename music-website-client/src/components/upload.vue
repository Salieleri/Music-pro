<template>
  <div class="upload">
    <p class="title">修改头像</p>
    <hr>
    <div class="section">
      <el-upload
        drag
        :action="upload()"
        :show-file-list="false"
        :on-success="handleAvatorsuccess"
        :before-upload="beforeupload">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖至此处，或<em>修改头像</em></div>
        <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过10MB</div>
      </el-upload>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
import mixin from '@/mixin'
export default {
  name: 'upload',
  mixins: [mixin],
  data () {
    return {
      imageurl: ''
    }
  },
  computed: {
    ...mapGetters([
      'userid',
      'avator'
    ])
  },
  methods: {
    upload () {
      return `${this.$store.state.configure.host}/user/avator/upload?id=${this.userid}&past=${this.avator}`
    },
    handleAvatorsuccess (res) {
      if (res.code === 1) {
        this.$store.commit('setavator', res.avator)
        this.notify(res.mes, 'success')
        setTimeout(() => {}, 2000)
        this.$router.push('/')
      } else {
        this.notify(res.mes, 'error')
      }
    },
    beforeupload (file) {
      const isJPG = file.type === 'image/jpeg'
      const enoughsize = file.size / 1024 / 1024 < 10
      if (!isJPG) {
        this.notify('上传头像只能上传jpg/png格式!', 'error')
      }
      if (!enoughsize) {
        this.notify('上传图片不能超过10MB!', 'error')
      }
      return isJPG && enoughsize
    }
  }
}
</script>

<style scoped>
@import '../assets/scss/upload.scss';
</style>
