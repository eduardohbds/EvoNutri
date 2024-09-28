<script setup lang="ts"></script>

<template>
 <div class="container">
    <div class="py-3 text-center">
      <h2>Evonutri</h2>
      <!-- <p class="lead">Cadastre-se já para ter acesso ao nosso serviço!</p> -->
    </div>
    <div class="row g-5 mb-3 mt-3">
      <div class="col-md-12">
        <h4>Faça sua consulta rápida com o NutriBot!</h4>

        <!-- <div class="alert alert-danger" v-if="validationMessage" role="alert">
          {{ validationMessage }}
        </div> -->
        <hr class="my-4" />
        <form class="needs-validation" novalidate>
          <div class="row g-4">
            <div class="col-12">
              <label class="form-label">Nome</label>
              <div class="input-group has-validation">
                <input type="name" class="form-control" required />
                <div class="invalid-feedback">Your username is required.</div>
              </div>
            </div>
            <div class="col-6">
              <label class="form-label">Idade</label>
              <div class="input-group has-validation">
                <input type="name" class="form-control" required />
                <div class="invalid-feedback">Your username is required.</div>
              </div>
            </div>
            
            <div class="col-6">
              <label class="form-label">Sexo</label>
              <div class="form-check">
                <input
                  type="radio"
                  class="form-check-input"
                  id="nutriSim"
                  value="sim"
                  name="nutri"
                 
                  required
                />
                <label class="form-check-label" for="nutriSim">Masculino</label>
              </div>
              <div class="form-check">
                <input
                  type="radio"
                  class="form-check-input"
                  id="nutriSim"
                  value="não"
                  name="nutri"
                 
                  required
                />
                <label class="form-check-label" for="nutriNao">Feminino</label>
              </div>
              
              
            </div>
            <div class="col-6">
              <label class="form-label">Peso</label>
              <div class="input-group has-validation">
                <input type="name" class="form-control" required />
                <div class="invalid-feedback">Your username is required.</div>
              </div>
            </div>
            <div class="col-6">
              <label class="form-label">Altura</label>
              <div class="input-group has-validation">
                <input type="name" class="form-control" required />
                <div class="invalid-feedback">Your username is required.</div>
              </div>
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
}</style>
