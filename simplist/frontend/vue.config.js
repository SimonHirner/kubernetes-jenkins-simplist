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
        target: "http://192.168.49.2:30010",
        ws: true,
        changeOrigin: true,
      },
    },
  },
};
