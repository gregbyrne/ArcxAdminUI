module.exports = {
  chainWebpack: config => {
    config
        .plugin('html')
        .tap(args => {
          args[0].title = 'ARC-X Admin'
          return args
        })
  },
  devServer: {
    disableHostCheck: true
  }
}
