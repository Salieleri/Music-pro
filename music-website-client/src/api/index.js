import {post} from './request'

const http = {
  loginin: (param) => post('/user/login', param)
}

export {http}
