import {BASE_URL} from './config'
import router from '../router'
import axios from 'axios'

axios.defaults.timeout = 5000 // 超时时间设置
axios.defaults.withCredentials = true // true允许跨域
// Content-Type 响应头
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'

axios.defaults.baseURL = BASE_URL

axios.interceptors.response.use(
  response => {
    if (response.status === 200) {
      return Promise.resolve(response)
    } else {
      return Promise.reject(response)
    }
  },

  error => {
    if (error.response.status) {
      switch (error.response.status) {
        case 401:
          router.replace({
            path: '/'
          })
          break
        case 403:
          setTimeout(() => {
            router.replace({
              path: '/'
            })
          }, 1000)
          break
        case 404:
          break
      }
      return Promise.reject(error)
    }
  }
)

export function get (url, param = {}, responsetype = 'json') {
  return new Promise((resolve, reject) => {
    axios.get(url, {
      params: param,
      responsetype
    })
      .then(response => {
        resolve(response.data)
      })
      .catch(err => {
        reject(err)
      })
  })
}

export function post (url, data = {}) {
  return new Promise((resolve, reject) => {
    axios.post(url, data)
      .then(response => {
        resolve(response.data)
      })
      .catch(err => {
        reject(err)
      })
  })
}
