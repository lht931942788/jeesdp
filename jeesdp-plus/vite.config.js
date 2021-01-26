import vuePlugin from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'

const path = require('path')

export default {
    alias: {
        '/@/': path.resolve(__dirname, './src')
    },
    open: true,
    optimizeDeps: {
        include: ["element-plus/lib/locale", "element-plus/lib/locale/lang/zh-cn"],
        auto: true
    },
    plugins: [
        vuePlugin(),
        vueJsx({
            // options are passed on to @vue/babel-plugin-jsx
        })
    ],
    server: {
        port: 8080,
    }
    /*proxy: {
        '/api': {
            target: 'https://blog.csdn.net/weixin_45292658',
            changeOrigin: true,
            rewrite: path => path.replace(/^\/api/, '')
        }
    }*/
}
