<template functional>
    <el-submenu v-if="props['menu'].children && props['menu'].children.length > 0"
                :index="props['menu'].url && props['menu'].url !== ''?props['menu'].url:props['menu'].id">
        <template slot="title">
            <i v-if="props['menu'].iconClass" :class="props['menu'].iconClass"></i>
            <span slot="title">{{props['menu'].name}}</span>
        </template>
        <template v-for="item in props['menu'].children">
            <sub-menu :menu="item"></sub-menu>
        </template>
    </el-submenu>
    <el-menu-item v-else :index="props['menu'].url && props['menu'].url !==''?props['menu'].url:props['menu'].id">
        <i v-if="props['menu'].iconClass" :class="props['menu'].iconClass"></i>
        <span slot="title">{{props['menu'].name}}</span>
    </el-menu-item>
</template>
<script>
    import SubMenu from './SubMenu'

    export default {
        name: 'SubMenu',
        props: {
            menu: {
                type: Object,
                required: true
            }
        },
        components: {
            SubMenu,
        },
    };
</script>