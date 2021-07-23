const MonacoWebpackPlugin = require('monaco-editor-webpack-plugin')
;
module.exports = {
    devServer: {
        port: 80, // 端口
    },
    lintOnSave: false, // 取消 eslint 验证,
    configureWebpack: config => {
        config.plugins.push(new MonacoWebpackPlugin({
            languages: ["typescript", "javascript", "css", "sql", "java"],
        }))
    }
};