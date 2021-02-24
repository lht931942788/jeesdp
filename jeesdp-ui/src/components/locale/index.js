import {createI18n} from "vue-i18n";
import enLocale from "element-plus/lib/locale/lang/en";
import zhLocale from "element-plus/lib/locale/lang/zh-cn";

const locale = createI18n({
    locale: zhLocale.name,
    fallbackLocale: zhLocale.name,
    messages: {
        [enLocale.name]: {
            el: enLocale.el,
            message: {
                hello: 'hello world',
            },
        },
        [zhLocale.name]: {
            el: zhLocale.el,
            message: {
                hello: 'Please select a record',
            },
            button: {
                add: '新增'
            }
        },
    },
})
export default locale;
