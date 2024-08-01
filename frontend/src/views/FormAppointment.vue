<script setup lang="ts">
import { ref, onMounted } from 'vue';

const isNutri = ref(false);
</script>

<template>
  <div class="container">
    <div class="py-3 text-center">
      <h2>Evonutri</h2>
      <!-- <p class="lead">Cadastre-se já para ter acesso ao nosso serviço!</p> -->
    </div>
    <div class="row g-5 mb-3 mt-3">
      <div class="col-md-12">
        <h4>Agendar consulta</h4>
        <!-- <div class="alert alert-danger" v-if="validationMessage" role="alert">
          {{ validationMessage }}
        </div> -->
        <hr class="my-4" />
        <form class="needs-validation" novalidate>
          <div class="row g-4">
            <div class="col-12">
              <label class="form-label">Profissional</label>
              <select id="inputState" class="form-control">
                <option selected>Profissionais...</option>
                <option>...</option>
              </select>
            </div>
            <div class="col-6">
              <label class="form-label">Horários</label>
              <select id="inputState" class="form-control">
                <option selected>Horários...</option>
                <option>...</option>
              </select>
            </div>
          </div>

          <hr class="my-4" />
          <div class="d-flex justify-content-end">
            <RouterLink to="/" type="button" class="btn btn-danger btn-lg me-2"
              >Cancelar</RouterLink
            >

            <button class="btn btn-primary btn-lg" type="submit">
              Cadastre-se
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  max-width: 600px;
}
</style>
