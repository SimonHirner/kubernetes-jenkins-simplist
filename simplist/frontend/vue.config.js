module.exports = {
  // Change build paths to make them Maven compatible
  // see https://cli.vuejs.org/config/
  outputDir: "target/dist",
  assetsDir: "static",
  configureWebpack: {
    devtool: "source-map",
  },
  devServer: {
    port: 8080,
    proxy: {
      "/api": {
        target: process.env.VUE_APP_BACKEND_URL,
        ws: true,
        changeOrigin: true,
      },
    },
  },
};
