import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/Home.vue';
import FormUser from '@/views/FormUser.vue';
import FormLogin from '@/views/FormLogin.vue';
import FormAppointment from '@/views/FormAppointment.vue';
import FormConsulta from '@/views/FormConsulta.vue';
import FormMedicao from '@/views/FormMedicao.vue';


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
    {
      path: '/NewAppointment',
      component: FormAppointment,
      name: 'Appointment',
    },
    {
      path: '/PreConsultaForm',
      component: FormConsulta,
      name: 'Consulta',
    },
    {
      path: '/MedicaoForm',
      component: FormMedicao,
      name: 'Medição',
    },
  ],
});

export default router;
