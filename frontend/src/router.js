
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import OrderServiceOrderManager from "./components/listers/OrderServiceOrderCards"
import OrderServiceOrderDetail from "./components/listers/OrderServiceOrderDetail"

import SupportServiceDeliveryManager from "./components/listers/SupportServiceDeliveryCards"
import SupportServiceDeliveryDetail from "./components/listers/SupportServiceDeliveryDetail"
import SupportServiceInventoryManager from "./components/listers/SupportServiceInventoryCards"
import SupportServiceInventoryDetail from "./components/listers/SupportServiceInventoryDetail"


import MyViewView from "./components/MyViewView"
import MyViewViewDetail from "./components/MyViewViewDetail"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/orderServices/orders',
                name: 'OrderServiceOrderManager',
                component: OrderServiceOrderManager
            },
            {
                path: '/orderServices/orders/:id',
                name: 'OrderServiceOrderDetail',
                component: OrderServiceOrderDetail
            },

            {
                path: '/supportServices/deliveries',
                name: 'SupportServiceDeliveryManager',
                component: SupportServiceDeliveryManager
            },
            {
                path: '/supportServices/deliveries/:id',
                name: 'SupportServiceDeliveryDetail',
                component: SupportServiceDeliveryDetail
            },
            {
                path: '/supportServices/inventories',
                name: 'SupportServiceInventoryManager',
                component: SupportServiceInventoryManager
            },
            {
                path: '/supportServices/inventories/:id',
                name: 'SupportServiceInventoryDetail',
                component: SupportServiceInventoryDetail
            },


            {
                path: '/dashboardServices/myViews',
                name: 'MyViewView',
                component: MyViewView
            },
            {
                path: '/dashboardServices/myViews/:id',
                name: 'MyViewViewDetail',
                component: MyViewViewDetail
            },


    ]
})
