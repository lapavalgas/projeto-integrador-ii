import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/components/pages/Home.vue'
import Sobre from '@/components/pages/Sobre.vue'
import Contato from '@/components/pages/Contato.vue'
import Cadastrar from '@/components/pages/Cadastrar.vue'
import Conta from '@/components/pages/Conta.vue'
import Servicos from '@/components/pages/Servicos.vue'
import Checkout from '@/components/pages/Checkout.vue'
import ServicosOfertar from '@/components/pages/ServicosOfertar.vue'

Vue.use(VueRouter)

const routes = [{
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/sobre',
    name: 'Sobre',
    component: Sobre
  },
  {
    path: '/contato',
    name: 'Contato',
    component: Contato
  },
  {
    path: '/cadastrar',
    name: 'Cadastrar',
    component: Cadastrar
  },
  {
    path: '/conta',
    name: 'Conta',
    component: Conta
  },
  {
    path: '/servicos',
    name: 'Servicos',
    component: Servicos
  },
  {
    path: '/ofertar',
    name: 'ServicosOfertar',
    component: ServicosOfertar
  },
  {
    path: '/checkout',
    name: 'Checkout',
    component: Checkout
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router