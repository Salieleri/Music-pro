const user = {
  state: {
    userid: '',
    avator: ''
  },
  getters: {
    userid: state => {
      let userid = state.userid
      if (!userid) {
        userid = JSON.parse(window.sessionStorage.getItem('userid' || null))
      }
      return userid
    },
    avator: state => {
      let Avator = state.avator
      if (!Avator) {
        Avator = JSON.parse(window.sessionStorage.getItem('avator' || null))
      }
      return Avator
    }
  },
  mutations: {
    setuserId: (state, userid) => {
      state.userid = userid
      window.sessionStorage.setItem('userid', JSON.stringify(userid))
    },
    setavator: (state, avator) => {
      state.avator = avator
      window.sessionStorage.setItem('avator', JSON.stringify(avator))
    }
  }
}

export default user
