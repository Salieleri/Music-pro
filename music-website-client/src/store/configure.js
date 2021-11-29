import {BASE_URL} from '@/api/config'

const configure = {
  state: {
    host: BASE_URL,
    seachewords: '',
    activeName: '',
    loginin: false,
    isactive: false,
    index: 0
  },
  getters: {
    searchwords: state => state.searchwords,
    activeName: state => {
      let activeName = state.activeName
      if (!activeName) {
        activeName = JSON.parse(window.sessionStorage.getItem('activeName'))
      }
      return activeName
    },
    loginin: state => {
      let loginin = state.loginin
      if (!loginin) {
        loginin = JSON.parse(window.sessionStorage.getItem('loginin'))
      }
      return loginin
    },
    isactive: state => {
      let isactive = state.isactive
      if (!isactive) {
        isactive = JSON.parse(window.sessionStorage.getItem('isactive'))
      }
      return isactive
    },
    index: state => {
      let index = state.index
      if (!index) {
        index = JSON.parse(window.sessionStorage.getItem('ubdex'))
      }
      return index
    }
  },
  mutations: {
    setsearchwords: (state, searchwords) => { state.searchwords = searchwords },
    setactiveName: (state, activeName) => {
      state.activeName = activeName
      window.sessionStorage.setItem('activename', JSON.stringify(activeName))
    },
    setloginin: (state, loginin) => {
      state.loginin = loginin
      window.sessionStorage.setItem('loginin', JSON.stringify(loginin))
    },
    setisactive: (state, isactive) => {
      state.isactive = isactive
      window.sessionStorage.setItem('isactive', JSON.stringify(isactive))
    },
    setindex: (state, index) => {
      state.index = index
      window.sessionStorage.setItem('index', JSON.stringify(index))
    }
  },
  actions: {}
}

export default configure
