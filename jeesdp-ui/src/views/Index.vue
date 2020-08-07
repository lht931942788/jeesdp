<template>
    <el-container>
        <el-header style="background: #545c64">
            <!--<div style="float: left">
                 <el-button style="line-height: 44px;font-size: 24px;color: white" @click="collapse = !collapse"
                            type="text" :icon="collapse?'el-icon-s-unfold':'el-icon-s-fold'"></el-button>
            </div>-->
            <div class="user">
                <el-popover :visible-arrow="false" trigger="click" width="250">
                    <div slot="reference" style="height: 40px;color: white;line-height: 40px;margin: 10px 10px">
                        <div style="float: right;line-height: 40px">
                            <span>admin</span>
                        </div>
                        <div style="float: right;margin-right: 10px">
                            <el-avatar :size="40" src="/img/user/circle.png"></el-avatar>
                        </div>
                    </div>
                    <div style="width: 100%;text-align: center">
                        <div>
                            <el-avatar :size="100" src="/img/user/demo.jpg"></el-avatar>
                        </div>
                        <div>

                        </div>
                    </div>
                    <div style="text-align: center;height: 40px;">
                        <div style="float: left;width: 50%;height: 40px;line-height: 40px">
                            <el-button @click="logout" icon="el-icon-setting" style="font-size: 20px;"
                                       type="text"></el-button>
                        </div>
                        <div style="float: left;width: 50%;height: 40px;;line-height: 40px">
                            <el-button @click="logout" icon="el-icon-switch-button" style="font-size: 20px;"
                                       type="text"></el-button>
                        </div>
                    </div>
                </el-popover>
            </div>
            <div class="message" style="float: right">
                <el-popover :visible-arrow="false" trigger="click" width="300">
                    <el-badge :value="12" class="mark" slot="reference"
                              style="color: white;margin-right: 20px;margin-top: 20px;font-size: 20px">
                        <i class="el-icon-bell"></i>
                    </el-badge>
                    <el-tabs :stretch="true" style="height: 340px" v-model="activeName">
                        <el-tab-pane label="消息" name="message" style="height: 300px">
                            <div style="height: 240px;width: 100%;overflow: auto">
                                <div>
                                    aaa
                                </div>
                                <el-divider></el-divider>
                                <div>
                                    aaa
                                </div>
                                <el-divider></el-divider>
                                <div>
                                    aaa
                                </div>
                                <el-divider></el-divider>
                                <div>
                                    aaa
                                </div>
                                <el-divider></el-divider>
                            </div>
                            <div style="text-align: center;height: 40px;">
                                <div style="float: left;width: 50%;height: 40px;line-height: 40px">
                                    <el-link :underline="false">全部已读</el-link>
                                </div>
                                <div style="float: left;width: 50%;height: 40px;;line-height: 40px">
                                    <el-link :underline="false">查看更多</el-link>
                                </div>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="代办任务" name="second">配置管理</el-tab-pane>
                    </el-tabs>
                </el-popover>
            </div>
        </el-header>
        <el-container>
            <el-aside :width="collapse?'56px':'220px'" style="background: #545c64">
                <el-menu :default-active="defaultActive" :collapse="collapse"
                         @select="select" :unique-opened="true" :router="true"
                         active-text-color="#ffd04b" background-color="#545c64" text-color="#fff">
                    <sub-menu v-for="menu in menus" :menu="menu"></sub-menu>
                </el-menu>
            </el-aside>
            <el-container>
                <el-main style="height: 100%">
                    <router-view></router-view>
                </el-main>
                <el-footer style="text-align: center" height="30">Footer</el-footer>
            </el-container>
        </el-container>
    </el-container>
</template>
<script>

    import SubMenu from '../components/SubMenu';
    import Oauth from "../utils/oauth";

    export default {
        name: 'Index',
        components: {SubMenu},
        data() {
            return {
                collapse: false,
                defaultActive: '',
                activeName: 'message',
                menus: [],
                tableData: [{
                    date: '2016-05-02',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄',
                }, {
                    date: '2016-05-04',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄',
                }, {
                    date: '2016-05-03',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄',
                }, {
                    date: '2016-05-03',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄',
                }, {
                    date: '2016-05-03',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄',
                }, {
                    date: '2016-05-03',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄',
                }, {
                    date: '2016-05-03',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }]
            }
        },
        methods: {
            select(index, indexPath) {
                this.defaultActive = index;
                this.$cookie.set('defaultActive', index);
            },
            logout() {
                Oauth.logout()
            }
        },
        created() {
            let defaultActive = this.$cookie.get('defaultActive');
            if (defaultActive) {
                this.defaultActive = defaultActive;
            }
            this.menus = loadMenu(this.menus, this.$user.resources);
        }
    };

    function loadMenu(menus, resources) {
        if (resources) {
            for (let i = 0; i < resources.length; i++) {
                if (resources[i].type === '1') {
                    let menu = {
                        id: resources[i].id,
                        name: resources[i].name,
                        url: resources[i].url,
                        //iconClass: resources[i].iconClass,
                        children: loadMenu([], resources[i].children)
                    };
                    menus.push(menu);
                }
            }
        }
        return menus;
    }
</script>