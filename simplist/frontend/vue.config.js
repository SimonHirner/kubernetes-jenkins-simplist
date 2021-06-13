let backendURL = process.env.VUE_APP_BACKEND_URL;
if (backendURL == null) {
  backendURL = "http://127.0.0.1:8081";
}

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
        target: backendURL,
        ws: true,
        changeOrigin: true,
      },
    },
  },
};
