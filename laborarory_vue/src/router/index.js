import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import axios from "axios";

const routes = [
  {
    path: '/',
    redirect: '/login',
  },
  {
    path: '/home',
    component: HomeView,
    children: [
      {
        path: '',
        redirect: '/home/index'
      },
      {
        path: 'index',
        component: () => import('@/pages/index.vue'),
        name: '主页'
      },
      {
        path: 'repair',
        component: () => import('@/components/view/repair.vue'),
        name: '修理记录'
      },
      {
        path: 'apply',
        component: () => import('@/components/view/application.vue'),
        name: '购买申请'
      },
      {
        path: 'table',
        component: () => import('@/components/view/addApplication.vue'),
        name: '申请表'
      },
      {
        path: 'scrap',
        component: () => import('@/components/view/scrap.vue'),
        name: '报废记录'
      },
      {
        path: 'equipment',
        component: () => import('@/components/view/equipment.vue'),
        name: '设备管理'
      },
    ]
  },
  {
    path: '/login',
    component: () => import('@/pages/login.vue'),
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

// 前置路由守卫
router.beforeEach((to, from, next) => {
  console.log(to, from);
  if (to.name === '主页' || to.name === "设备管理" || to.name === '修理记录' || to.name === "报废记录" || to.name === "购买申请" || to.name === "申请表") {
    if (window.sessionStorage.getItem("token")) {
      next()
    }
    else {
      confirm("请先登录！")
    }
  }
  else {
    next()
  }
})

// 添加请求拦截器
axios.interceptors.request.use(function (config) {
  // 在发送请求之前做些什么
  // 判断是否存在token,如果存在将每个页面header添加token
  if (sessionStorage.getItem("token")) {
    config.headers.common['Authorization'] = "Bearer " + window.sessionStorage.getItem("token");
  }
  return config
}, function (error) {
  router.push('/login')
  return Promise.reject(error)
})
export default router
