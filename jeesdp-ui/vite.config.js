import vuePlugin from "@vitejs/plugin-vue";
import vueJsx from "@vitejs/plugin-vue-jsx";

const path = require("path");

export default ({command, mode}) => {
    return {
        resolve: {
            alias: {
                // 键必须以斜线开始和结束
                '/@/': path.resolve(__dirname, './src')
            }
        },
        build: {
            manifest: true
        },
        /*define: {
            BASE_URL: mode === "production" ? "http://localhost:8080" : "http://localhost:8090",
        },*/
        open: true,
        plugins: [
            vuePlugin(),
            vueJsx(),
        ],
        server: {
            port: 80,
        }
        /*proxy: {
            "/api": {
                target: "https://blog.csdn.net/weixin_45292658",
                changeOrigin: true,
                rewrite: path => path.replace(/^\/api/, "")
            }
        }*/
    }
}


