import {get, post} from './request'

const http = {
  // 用户API
  loginin: (param) => post(`/user/login`, param),

  signup: (param) => post(`/user/registry`, param),

  updateuser: (param) => post(`/user/update`, param),

  getuserofid: (param) => get(`/user/detail?id=${param}`),

  // 歌曲API
  getsongbysongname: (param) => get(`/Song/getbyname?name=${param}`),

  downloadMusic: (url) => get(url, {}, 'blob'),

  // 收藏API
  getiscollected: (param) => post(`/collect/confirm`, param),

  getcollection: (param) => get(`/collect/get?id=${param}`),

  setcollection: (param) => post(`/collect/add`, param),

  delcollection: (param) => post(`/collect/del`, param),

  // 歌单API
  getSongList: (param) => post(`/songlist/get`, param),

  getsonglistofstyle: (param) => post(`/songlist/style/get`, param),

  getnum: (param) => get(`/songlist/nums?style=${param}`),

  getsonginsonglist: (param) => get(`/songlist/getsong?id=${param}`),

  // 评论API
  getcomments: (param) => post(`/comment/get`, param),

  postcomment: (param) => post(`/comment/post`, param),

  postup: (param) => post(`/comment/like`, param)
}

export {http}
