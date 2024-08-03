  <script setup lang="ts"></script>

  <template>
    <header class="p-1 text-bg-dark">
      <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start mr-3">
          <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
            <!-- icone -->
            <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor"
              class="bi bi-heart-pulse-fill" viewBox="0 0 16 16">
              <path
                d="M1.475 9C2.702 10.84 4.779 12.871 8 15c3.221-2.129 5.298-4.16 6.525-6H12a.5.5 0 0 1-.464-.314l-1.457-3.642-1.598 5.593a.5.5 0 0 1-.945.049L5.889 6.568l-1.473 2.21A.5.5 0 0 1 4 9z" />
              <path
                d="M.88 8C-2.427 1.68 4.41-2 7.823 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C11.59-2 18.426 1.68 15.12 8h-2.783l-1.874-4.686a.5.5 0 0 0-.945.049L7.921 8.956 6.464 5.314a.5.5 0 0 0-.88-.091L3.732 8z" />
            </svg>
          </a>

          <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
            <li style="margin-left: 10px">
              <RouterLink to="/" class="nav-link px-2 text-white">Home</RouterLink>
            </li>
            <!-- <li>
              <RouterLink to="/home/myMovies" class="nav-link px-2 text-white"
                >Meus Filmes</RouterLink
              >
            </li> -->
            <li>
              <RouterLink to="/NewAppointment" class="nav-link px-2 text-white">Agendar consulta</RouterLink>
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle text-white"
                href="#"
                id="navbarDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Anamnese
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li>
                  <RouterLink to="/MedicaoForm" class="dropdown-item">
                    Form Medicao
                  </RouterLink>
                </li>
                <li>
                  <RouterLink to="/PreConsultaForm" class="dropdown-item">
                    Form Consulta
                  </RouterLink>
                </li>
              </ul>
            </li>
            <!-- <li
              v-if="
                userStore.user.role === 'admin' ||
                userStore.user.role === 'funcionario'
              "
            >
              <RouterLink to="/adminSession" class="nav-link px-2 text-white"
                >Gerenciar Sessões</RouterLink
              >
            </li>
            <li v-if="userStore.user.role === 'admin'">
              <RouterLink to="/admin" class="nav-link px-2 text-white"
                >Gerenciar filmes</RouterLink
              >
            </li> -->
          </ul>

          <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
            <input type="search" class="form-control form-control-dark text-bg-dark" placeholder="Search..."
              aria-label="Search" />
          </form>
          <!-- <div
            v-if="userStore.user.username"
            class="nav text-end justify-content-center"
          >
            <h4 style="margin-right: 12px">{{ userStore.user.username }}</h4>

            <RouterLink
              @click="userStore.logout"
              to="/"
              type="button"
              class="btn btn-secondary text-decoration-none"
              >Logout</RouterLink
            >
          </div> -->
          <div class="text-end">
            <RouterLink to="login" type="button" class="btn btn-primary me-2 text-decoration-none">Login</RouterLink>

            <RouterLink to="/FormUser" type="button" class="btn btn-secondary text-decoration-none">Cadastre-se
            </RouterLink>
          </div>
        </div>
      </div>
    </header>
  </template>

<style scoped></style>
