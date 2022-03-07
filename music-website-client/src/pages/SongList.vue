<template>
  <div class="song-list">
    <ul class="song-list-header">
      <li
        v-for="(item, index) in songStyle"
        :key="index"
        :class="{active: item.name === activeName}"
        @click="handleChangeView(item.name)">
        {{item.name}}
      </li>
    </ul>
    <div>
      <contentlist :contentlist="albumdata"></contentlist>
    </div>
    <div class="pagination">
      <el-pagination
        @current-change="handleCurrentChange"
        background
        layout="total, prev, pager, next"
        :current-page="currentpage"
        :page-size="pagesize"
        :total="length">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {http} from '@/api'
import {songStyle} from '@/assets/data/SongStyle'
import Contentlist from '@/components/Contentlist'
export default {
  name: 'SongList',
  components: {
    Contentlist
  },
  data () {
    return {
      songStyle: songStyle,
      currentpage: 1,
      pagesize: 15,
      albumdata: [],
      activeName: '全部歌单',
      length: 0
    }
  },
  methods: {
    handleCurrentChange (val) {
      this.currentpage = val
    },
    handleChangeView (item) {
      this.activeName = item
      this.albumdata = []
      if (item === '全部歌单') {
        this.getsonglist(this.pagesize, 1)
      } else {
        this.getsonglistofstyle(item, this.pagesize, 1)
      }
      this.getnum(item)
    },
    getsonglist (maxdata, page) {
      let mess = {
        'maxdata': maxdata,
        'page': page - 1
      }
      http.getSongList(mess)
        .then(res => {
          this.albumdata = res
        })
        .catch(err => {
          console.log(err)
        })
    },
    getsonglistofstyle (item, maxdata, page) {
      let mess = {
        'style': item,
        'maxdata': maxdata,
        'page': page - 1
      }
      http.getsonglistofstyle(mess)
        .then(res => {
          this.albumdata = res
        })
        .catch(err => {
          console.log(err)
        })
    },
    getnum (item) {
      http.getnum(item)
        .then(res => {
          this.length = res
        })
        .catch(err => {
          console.log(err)
        })
    }
  },
  mounted () {
    this.handleChangeView('全部歌单')
  }
}
</script>

<style scoped>
@import "../assets/scss/songlist.scss";
</style>
