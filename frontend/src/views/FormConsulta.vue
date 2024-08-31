<template>
    <div class="container mt-5">
        <form @submit.prevent="submitForm">
            <div class="mb-3">
                <label for="consultaInfo" class="form-label">Informações Consulta</label>
                <textarea id="consultaInfo" class="form-control" v-model="form.consultaInfo"></textarea>
            </div>
            <div class="mb-3">
                <label for="historicoPessoal" class="form-label">Histórico pessoal e social</label>
                <textarea id="historicoPessoal" class="form-control" v-model="form.historicoPessoal"></textarea>
            </div>
            <div class="mb-3">
                <label for="historicoAlimentar" class="form-label">Histórico alimentar</label>
                <textarea id="historicoAlimentar" class="form-control" v-model="form.historicoAlimentar"></textarea>
            </div>
            <div class="mb-3">
                <label for="historicoGestacional" class="form-label">Histórico gestacional</label>
                <textarea id="historicoGestacional" class="form-control" v-model="form.historicoGestacional"></textarea>
            </div>
            <div class="mb-3">
                <label for="observacoes" class="form-label">Observações</label>
                <textarea id="observacoes" class="form-control" v-model="form.observacoes"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
        </form>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
    name: 'PreConsultaForm',
    data() {
        return {
            form: {
                consultaInfo: '',
                historicoPessoal: '',
                historicoAlimentar: '',
                historicoGestacional: '',
                observacoes: ''
            }
        };
    },
    methods: {
        submitForm() {
            console.log('Form submitted:', this.form);
            // Add your form submission logic here
        }
    }
});
</script>

<style scoped>
.container {
    max-width: 600px;
}
</style>