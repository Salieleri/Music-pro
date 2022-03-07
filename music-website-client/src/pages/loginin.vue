<template>
<div class="login">
  <div class="login-head">
    <span>账号登录</span>
  </div>
  <el-form :model="loginform" status-icon :rules="rules" ref="loginform">
    <el-form-item prop="username">
      <el-input placeholder="用户名" v-model="loginform.username"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" placeholder="密码" v-model="loginform.password" @keyup.enter.native="login"></el-input>
    </el-form-item>
    <div class="login-btn">
      <el-button>注册</el-button>
      <el-button @click="login" type="primary">登录</el-button>
    </div>
  </el-form>
</div>
</template>

<script>
import {http} from '../api/index'
import mixin from '../mixin/index'
import {mapGetters} from 'vuex'

export default {
  name: 'login',
  mixins: [mixin],
  data: () => {
    let validatename = (rules, values, callback) => {
      if (!values) {
        return callback(new Error('用户名不能为空！'))
      } else {
        callback()
      }
    }
    let validatepasswd = (rules, values, callback) => {
      if (values === '') {
        return callback(new Error('请输入密码!'))
      } else {
        callback()
      }
    }
    return {
      loginform: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          {validator: validatename, message: '请输入用户名', trigger: 'blur'}
        ],
        password: [
          {validator: validatepasswd, message: '请输入密码', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    login () {
      let _this = this
      let param = {
        'username': this.loginform.username,
        'password': this.loginform.password
      }
      http.loginin(param)
        .then(res => {
          if (res.code === 1) {
            _this.notify('用户名或密码错误！', 'error')
          } else {
            _this.notify('登陆成功', 'success')
            _this.$store.commit('setloginin', true)
            setTimeout(() => {
              _this.$router.push('/')
            }, 2000)
            _this.setUserMsg(res.usermsg)
          }
        })
        .catch(err => {
          console.log(err)
        })
    },
    setUserMsg (item) {
      this.$store.commit('setuserId', item.userId)
      this.$store.commit('setavator', item.userAvator)
    }
  },
  computed: {
    ...mapGetters([
      'userid',
      'avator'
    ])
  }
}
</script>

<style scoped>
@import '../assets/scss/loginin.scss';
</style>

<style>
.login-btn{
  display: block;
  width: 50%;
}
</style>
