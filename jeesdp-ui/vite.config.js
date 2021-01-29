import vuePlugin from "@vitejs/plugin-vue";
import vueJsx from "@vitejs/plugin-vue-jsx";

const path = require("path");

export default ({command, mode}) => {
    console.log(command, mode)
    return {
        alias: {
            "/@/": path.resolve(__dirname, "./src")
        },
        build: {
            manifest: true
        },
        define: {
            host: mode === "production" ? "http://localhost:8080" : "http://localhost:8090",
        },
        open: true,
        optimizeDeps: {
            include: [
                "element-plus/lib/locale",
                'element-plus/lib/locale/lang/en',
                "element-plus/lib/locale/lang/zh-cn",
            ],
            exclude: [],
            auto: true
        },
        plugins: [
            vuePlugin(),
            vueJsx(),
        ],
        server: {
            port: 8080,
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


