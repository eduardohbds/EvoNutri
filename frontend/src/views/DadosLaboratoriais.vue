<template>
    <div class="container mt-5">
        <h5>Dados Laboratoriais</h5>
        <form @submit.prevent="submitData">
            <div v-for="(field, index) in laboratoryFields" :key="index" class="mb-3">
                <label :for="field.id" class="form-label">{{ field.label }}</label>
                <input :id="field.id" type="text" class="form-control" v-model="field.value" />
            </div>
            <button type="submit" class="btn btn-primary">Salvar</button>
        </form>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
    name: 'DadosLaboratoriais',
    data() {
        return {
            laboratoryFields: [
                { id: 'colesterolHDL', label: 'Colesterol HDL', value: '' },
                { id: 'colesterolLDL', label: 'Colesterol LDL', value: '' },
                { id: 'colesterolTotal', label: 'Colesterol total', value: '' },
                { id: 'pressaoArterialDiastolica', label: 'Pressão arterial diastólica', value: '' },
                { id: 'pressaoArterialSistolica', label: 'Pressão arterial sistólica', value: '' },
                { id: 'triglicerideos', label: 'Triglicerídeos', value: '' }
            ]
        };
    },
    methods: {
        submitData() {
            console.log('Dados laboratoriais:', this.laboratoryFields);
        }
    }
});
</script>

<style scoped>
.container {
    max-width: 600px;
}
</style>