<template>
    <div class="container mt-5">
        <h5>Progresso</h5>
        <line-chart :chart-data="chartData" :options="chartOptions"></line-chart>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref, watch } from 'vue';
import { Line } from 'vue-chartjs';
import {
    Chart as ChartJS,
    Title,
    Tooltip,
    Legend,
    LineElement,
    CategoryScale,
    LinearScale,
    PointElement
} from 'chart.js';

ChartJS.register(Title, Tooltip, Legend, LineElement, CategoryScale, LinearScale, PointElement);

export default defineComponent({
    name: 'Progresso',
    components: {
        LineChart: Line
    },
    props: {
        data: {
            type: Array,
            required: true
        }
    },
    setup(props) {
        const chartData = ref({
            labels: props.data.map(d => d.date),
            datasets: [
                {
                    label: 'Progresso',
                    data: props.data.map(d => d.value),
                    fill: false,
                    borderColor: 'blue',
                    tension: 0.1
                }
            ]
        });

        const chartOptions = ref({
            responsive: true,
            maintainAspectRatio: false
        });

        watch(
            () => props.data,
            (newData) => {
                chartData.value.labels = newData.map(d => d.date);
                chartData.value.datasets[0].data = newData.map(d => d.value);
            },
            { deep: true }
        );

        return {
            chartData,
            chartOptions
        };
    }
});
</script>

<style scoped>
.container {
    max-width: 600px;
}
</style>