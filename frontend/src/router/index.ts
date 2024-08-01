import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/Home.vue';
import FormUser from '@/views/FormUser.vue';
import FormLogin from '@/views/FormLogin.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/formUser',
      name: 'FormUser',
      component: FormUser,
    },
    {
      path: '/login',
      component: FormLogin,
      name: 'login',
      alias: '/login',
    },
  ],
});

export default router;
