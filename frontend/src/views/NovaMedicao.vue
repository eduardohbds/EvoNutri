<template>
    <div class="container mt-5">
        <h5>Nova Medição</h5>
        <form @submit.prevent="registerMeasurement">
            <div class="mb-3">
                <label for="data" class="form-label">Data</label>
                <input id="data" type="date" class="form-control" v-model="measurement.date" />
            </div>
            <div class="mb-3">
                <label for="valor" class="form-label">Valor</label>
                <input id="valor" type="number" class="form-control" v-model="measurement.value" />
            </div>
            <div class="mb-3">
                <label for="unidade" class="form-label">Unidade</label>
                <select id="unidade" class="form-control" v-model="measurement.unit">
                    <option value="kg">quilograma</option>
                    <option value="cm">centímetro</option>
                    <!-- Add more units as needed -->
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Registrar</button>
        </form>
        <Progresso :data="progressData" />
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Progresso from './Progresso.vue';

export default defineComponent({
    name: 'NovaMedicao',
    components: {
        Progresso
    },
    data() {
        return {
            measurement: {
                date: '',
                value: '',
                unit: 'kg'
            },
            progressData: []
        };
    },
    methods: {
        registerMeasurement() {
            this.progressData.push({ date: this.measurement.date, value: this.measurement.value });
            console.log('Nova Medição:', this.measurement);
        }
    }
});
</script>

<style scoped>
.container {
    max-width: 600px;
}
</style>