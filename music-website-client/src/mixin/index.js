import {mapGetters} from 'vuex'
import {http} from '@/api'
const mixin = {
  computed: {
    ...mapGetters([
      'userid',
      'loginin',
      'searchwords',
      'avator'
    ])
  },
  methods: {
    notify (title, type) {
      this.$notify({
        title,
        type
      })
    },
    getsong () {
      http.getsongbysongname(this.$route.query.keywords)
        .then(res => {
          if (res.length) {
            this.$store.commit('setlistOfSongs', res)
          } else {
            this.$store.commit('setlistOfSongs', [])
            this.notify('系统暂无此音乐', 'warning')
          }
        }).catch(err => {
          console.log(err)
        })
    },
    Play (id, url, pic, index, name, singer, lyric) {
      this.$store.commit('setId', id)
      this.$store.commit('setListIndex', index)
      this.$store.commit('setUrl', this.$store.state.configure.host + url)
      this.$store.commit('setpicUrl', this.$store.state.configure.host + pic)
      this.$store.commit('setTitle', name)
      this.$store.commit('setArtist', singer)
      this.$store.commit('setLyric', lyric)
      if (this.loginin) {
        this.$store.commit('setisactive', false)
        let mess = {
          'userid': this.userid,
          'id': id
        }
        http.getiscollected(mess)
          .then(res => {
            if (res.code === 1) {
              this.$store.commit('setisactive', true)
            }
          })
          .catch(err => {
            console.log(err)
          })
      }
    },
    attachImageUrl (url) {
      return url ? this.$store.state.configure.host + url : this.$store.state.configure.host + '/img/Avator/user.jpg'
    }
  }
}

export default mixin
