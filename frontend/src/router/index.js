import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home.vue'
import Goods from '../components/Goods.vue'
import NotFound from '../components/NotFound.vue'
import GoodsList from '../components/GoodsList.vue'

/* eslint-disable */
Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
    routes: [
        { path: '/', component: Home },
        { path: '/goods', component: Goods },
        { path: '/goodsList', component: GoodsList },
        { path: '*', component: NotFound } //마지막은 다 여기로. if else와 같이
    ]
})
export default router