<script setup lang="ts"></script>

<template>
  <div class="yep row justify-content-center">
    <div class="col-3 card">
      <div class="card-body">
        <h5 class="card-title">Fazer login</h5>
        <!-- Onde o feedback do form vai ficar -->
        <!-- <div v-if="errorMessage" class="alert alert-danger" role="alert">
          {{ errorMessage }}
        </div> -->
        <!-- novalidate diz pro navegador que a gente é que vai cuidar do form -->
        <!--@submit.prevent="authenticate" é a função que é chamada ao apertar o botão de submit  -->
        <!--class="{'was-validated': submitted}" é usada para mostrar as cores no imput somente quando o botão de submit é apertado pela primeira vez-->
        <form novalidate>
          <div class="mb-3">
            <label for="emailInput" class="form-label">Email:</label>
            <input
              type="email"
              class="form-control"
              id="emailInput"
              placeholder="mail@mail.com"
              required
            />
            <div class="invalid-feedback">
              Você deve informar um email válido.
            </div>
          </div>
          <div class="mb-3">
            <label for="passwordInput" class="form-label">Senha</label>
            <input
              type="password"
              class="form-control"
              id="passwordInput"
              required
            />
            <div class="invalid-feedback">A senha é um campo obrigatório.</div>
          </div>
          <div class="mb-3">
            <input
              type="submit"
              class="float-end btn btn-primary"
              value="Enviar"
            />
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>
.yep {
  margin-top: 100px;
}
</style>
