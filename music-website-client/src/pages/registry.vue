<template>
<div class="registry">
  <div class="registry-head">
    <span>账号注册</span>
  </div>
  <el-form :model="registryform" status-icon ref="registry" :rules="rules">
    <el-form-item prop="username" label="用户名">
      <el-input v-model="registryform.username" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item prop="password" label="密码">
      <el-input type="password" v-model="registryform.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item prop="phonenum" label="手机">
      <el-input v-model="registryform.phonenum" placeholder="手机"></el-input>
    </el-form-item>
    <div>
      <el-button @click="goback">取消</el-button>
      <el-button @click="signup" type="primary">注册</el-button>
    </div>
  </el-form>
</div>
</template>

<script>
import mixin from '../mixin'
import {http} from '@/api'

export default {
  name: 'registry',
  mixins: [mixin],
  data () {
    return {
      registryform: {
        username: '',
        password: '',
        phonenum: ''
      },
      rules: {
        username: [
          {message: '请输入用户名', required: true, trigger: 'blur'}
        ],
        password: [
          {message: '请输入密码', required: true, trigger: 'blur'}
        ],
        phonenum: [
          {message: '请输入手机号码', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    signup () {
      let _this = this
      let params = {
        'username': this.registryform.username,
        'password': this.registryform.password,
        'phonenum': this.registryform.phonenum
      }
      http.signup(params)
        .then(res => {
          if (res.code === 1) {
            _this.notify('注册失败!', 'error')
          } else {
            _this.notify('注册成功！', 'success')
            setTimeout(() => {
              _this.$store.commit('setactiveName', '')
              _this.$router.push('/')
            }, 2000)
          }
        })
    },
    goback () {
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>
@import '../assets/scss/registry.scss';
</style>
