//vue.config.js
const AutoImport = require('unplugin-auto-import/webpack')
const Components = require('unplugin-vue-components/webpack')
const { NaiveUiResolver } = require('unplugin-vue-components/resolvers')

const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  configureWebpack: {
    plugins: [
      AutoImport({
        resolvers: [NaiveUiResolver()],
      }),
      Components({
        resolvers: [NaiveUiResolver()],
      }),
    ],
    experiments: {
      topLevelAwait: true
    },
  },
  publicPath: process.env.NODE_ENV === "production" ? './' : '/',
})