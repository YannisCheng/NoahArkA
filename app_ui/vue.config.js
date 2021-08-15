module.exports = {
  chainWebpack: config => {
    const oneOfsMap = config.module.rule('scss').oneOfs.store
    oneOfsMap.forEach(item => {
      item
        .use('sass-resources-loader')
        .loader('sass-resources-loader')
        .options({
          // 要公用的scss的路径
          resources: './src/styles/global.scss'
        })
        .end()
    })
  },

  // 执行build打包，必要修改
  publicPath: './', // vue-cli3.3+新版本使用
  outputDir: 'dist',
  assetsDir: 'static',
  // 在打包时不产生.map文件，可以大大减小打包时的体积
  productionSourceMap: false
}

