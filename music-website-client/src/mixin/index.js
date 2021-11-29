import {mapGetters} from 'vuex'

const mixin = {
  computed: {
    ...mapGetters([
      'userid',
      'loginin'
    ])
  },
  methods: {
    notify (title, type) {
      this.$notify({
        title,
        type
      })
    }
  }
}

export default mixin
