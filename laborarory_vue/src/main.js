import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// 通用字体
import 'vfonts/Lato.css'
// 等宽字体
import 'vfonts/FiraCode.css'
// 引入字体图标
import '@/assets/fonts/iconfont.css'
//引入axios全局配置
import './api/config'




createApp(App).use(store).use(router).mount('#app')
