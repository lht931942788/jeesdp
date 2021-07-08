import vue from "@vitejs/plugin-vue";
import path from "path";

export default ({command, mode}) => {
    return {
        resolve: {
            alias: {
                // 键必须以斜线开始和结束
                '/@/': path.resolve(__dirname, './src')
            }
        },
        build: {
            manifest: true,
            rollupOptions: {
                plugins: []
            },
        },
        /*define: {
            BASE_URL: 'ss',
        },*/
        open: true,
        plugins: [
            vue(),
        ],
        server: {
            port: 80,
        },
        /*proxy: {
            "/api": {
                target: "https://blog.csdn.net/weixin_45292658",
                changeOrigin: true,
                rewrite: path => path.replace(/^\/api/, "")
            }
        }*/
    }
}


