<template>
    <div>
        <DadosAntropometricos />
        <DadosLaboratoriais />
        <NovaMedicao />
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import DadosAntropometricos from './DadosAntropometricos.vue';
import DadosLaboratoriais from './DadosLaboratoriais.vue';
import NovaMedicao from './NovaMedicao.vue';

export default defineComponent({
    name: 'FormMedicao',
    components: {
        DadosAntropometricos,
        DadosLaboratoriais,
        NovaMedicao
    }
});
</script>

<style scoped></style>