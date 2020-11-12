module.exports = {
  devServer: {
    port: 8081
  },
  chainWebpack: config => {
    config
        .plugin('html')
        .tap(args => {
          args[0].title = 'ARC-X Admin'
          return args
        })
  }
}
