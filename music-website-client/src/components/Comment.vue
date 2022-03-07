<template>
  <div>
    <div class="comment">
      <h2>
        <span>评论</span>
        <span class="part__tit_desc">共{{commentList.length}}条评论</span>
      </h2>
      <div class="comment-msg">
        <el-input
          class="comment-input"
          type="textarea"
          placeholder="期待您的精彩评论"
          :rows="2"
          v-model="textarea"
        ></el-input>
      </div>
      <el-button type="primary" class="sub-btn" @click="postcomment">发表评论</el-button>
    </div>
    <ul class="popular" v-for="(item, index) in commentList" :key="index">
      <li>
        <div class="popular-img">
          <img :src="attachImageUrl(userPic[index])">
        </div>
        <div class="popular-msg">
          <ul>
            <li class="name">{{userName[index]}}</li>
            <li class="content">{{item.comment_content}}</li>
            <li class="time">{{item.comment_create_time}}</li>
          </ul>
        </div>
        <div class="up" ref="up" @click="postup(item.comment_id, item.comment_like, index)">
          <svg class="icon" aria-hidden="true">
            <use :xlink:href="ZAN"></use>
          </svg>
          {{item.comment_like}}
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import {icon} from '@/assets/icon'
import {http} from '@/api'
import mixin from '@/mixin'
import {mapGetters} from 'vuex'
export default {
  name: 'Comment',
  mixins: [mixin],
  data () {
    return {
      commentList: [], // 存放评论内容
      userPic: [], // 保存评论用户头像
      userName: [], // 保存评论用户名字
      textarea: '', // 存放输入内容
      ZAN: icon.ZAN
    }
  },
  computed: {
    ...mapGetters([
      'userid',
      'avator',
      'loginin',
      'index'
    ])
  },
  props: {
    id: String, // 歌曲ID或歌单ID
    type: Number // 歌单（1）/歌曲（0）
  },
  mounted () {
    this.getcomments()
  },
  methods: {
    getcomments () {
      let mess = {
        'id': this.id,
        'type': this.type
      }
      http.getcomments(mess)
        .then(res => {
          this.commentList = res
          for (let item in res) {
            this.userPic.push(res[item].comment_user_avator)
            this.userName.push(res[item].comment_user_id)
          }
        })
    },
    postcomment () {
      let date = new Date()
      let createtime = date.getFullYear() + '-' + date.getMonth() + 1 + '-' + date.getDate() + ' ' +
      date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds()
      let mess = {
        'id': this.id,
        'type': this.type,
        'content': this.textarea,
        'userid': this.userid,
        'create_time': createtime,
        'avator': this.avator,
        'like': 0,
        'commentid': this.userid + createtime
      }
      if (this.loginin) {
        http.postcomment(mess)
          .then(res => {
            if (res.code === 1) {
              this.textarea = ''
              this.notify('评论成功', 'success')
              this.getcomments()
            } else {
              this.notify('评论失败', 'error')
            }
          })
          .catch(err => {
            console.log(err)
          })
      } else {
        this.notify('请先登录', 'warning')
      }
    },
    postup (id, like, index) {
      if (this.loginin) {
        let mess = {
          'id': id,
          'num': like + 1
        }
        http.postup(mess)
          .then(res => {
            if (res.code === 1) {
              this.$refs.up[index].children[0].style.color = '#2796dd'
              this.getcomments()
            }
          })
      } else {
        this.notify('请先登录', 'warning')
      }
    }
  }
}
</script>

<style scoped>
@import "../assets/scss/comment.scss";
</style>
