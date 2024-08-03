<template>
    <div class="container mt-5">
        <h5>Dados antropométricos</h5>
        <form @submit.prevent="submitData">
            <div v-for="(field, index) in anthropometricFields" :key="index" class="mb-3">
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
    name: 'DadosAntropometricos',
    data() {
        return {
            anthropometricFields: [
                { id: 'peso', label: 'Peso', value: '' },
                { id: 'altura', label: 'Altura', value: '' },
                { id: 'dobraCutaneaIliocristal', label: 'Dobra cutânea iliocristal', value: '' },
                { id: 'dobraCutaneaSuprailica', label: 'Dobra cutânea suprailiaca', value: '' },
                { id: 'dobraCutaneaAbdominal', label: 'Dobra cutânea abdominal', value: '' },
                { id: 'dobraCutaneaAxilarMedia', label: 'Dobra cutânea axilar média', value: '' },
                { id: 'dobraCutaneaDaCoxa', label: 'Dobra cutânea da coxa', value: '' },
                { id: 'dobraCutaneaPeitoral', label: 'Dobra cutânea peitoral', value: '' },
                { id: 'dobraCutaneaSubescapular', label: 'Dobra cutânea subescapular', value: '' },
                { id: 'dobraCutaneaSupraespinhal', label: 'Dobra cutânea supraespinhal', value: '' },
                { id: 'dobraCutaneaTricipital', label: 'Dobra cutânea tricipital', value: '' },
                { id: 'perimetroDaCintura', label: 'Perímetro da cintura', value: '' },
                { id: 'perimetroDoQuadril', label: 'Perímetro do quadril', value: '' }
            ]
        };
    },
    methods: {
        submitData() {
            console.log('Dados antropométricos:', this.anthropometricFields);
        }
    }
});
</script>

<style scoped>
.container {
    max-width: 600px;
}
</style>