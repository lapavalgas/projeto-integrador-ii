import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/components/pages/Home.vue'
import Sobre from '@/components/pages/Sobre.vue'
import Contato from '@/components/pages/Contato.vue'
import Cadastrar from '@/components/pages/Cadastrar.vue'
import Atualizar from '@/components/pages/Atualizar.vue'

Vue.use(VueRouter)

const routes = [
  {
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
    path: '/atualizar',
    name: 'Atualizar',
    component: Atualizar
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
