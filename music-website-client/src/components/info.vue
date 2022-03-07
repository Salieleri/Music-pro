<template>
  <div class="info">
    <p class="title">编辑个人资料</p>
  <hr>
    <div class="person">
      <el-form :model="registerForm" class="demo-ruleForm" label-width="80px">
        <el-form-item prop="username" label="用户名">
          <el-input v-model="registerForm.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码">
          <el-input v-model="registerForm.password" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item label="手机">
          <el-input v-model="registerForm.phonenumber" placeholder="手机"></el-input>
        </el-form-item>
      </el-form>
      <div class="btn">
        <div class="btn-child" @click="save">保存</div>
        <div class="btn-child" @click="goback">返回</div>
      </div>
    </div>
  </div>
</template>

<script>
import {http} from '@/api'
import {mapGetters} from 'vuex'
import mixin from '@/mixin'
export default {
  name: 'info',
  mixins: [mixin],
  data () {
    return {
      registerForm: {
        username: '',
        password: '',
        phonenumber: ''
      }
    }
  },
  computed: {
    ...mapGetters([
      'userid'
    ])
  },
  mounted () {
    this.getmsg(this.userid)
  },
  methods: {
    getmsg (id) {
      http.getuserofid(id)
        .then(res => {
          this.registerForm.username = res.userId
          this.registerForm.password = res.userPassword
          this.registerForm.phonenumber = res.userPhonenumber
        })
        .catch(err => {
          console.log(err)
        })
    },
    goback () {
      this.$router.go(-1)
    },
    save () {
      let params = {
        'before': this.userid,
        'id': this.registerForm.username,
        'password': this.registerForm.password,
        'phone': this.registerForm.phonenumber
      }
      http.updateuser(params)
        .then(res => {
          if (res.code === 1) {
            this.$store.commit('setuserId', this.registerForm.username)
            this.notify('修改成功', 'success')
            setTimeout(() => {
              this.$router.go(-1)
            }, 2000)
          } else {
            this.notify('修改失败', 'error')
          }
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}
</script>

<style scoped>
@import '../assets/scss/info.scss';
</style>
