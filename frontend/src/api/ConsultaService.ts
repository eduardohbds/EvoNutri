import { api } from "./index";
import { type consulta_infos } from "../types"
import { useUserStore } from "@/stores/userStore";

//"consulta-infos"
class ConsultaInfosService {
    async getAll(): Promise<consulta_infos[]> {
        const { data } = await api.get('/consulta-infos');
        //Retorna o objeto com todos os filme
        return data.data;
    }

    async getById(id: number): Promise<consulta_infos> {
        const { data } = await api.get(`/movies/${id}`);
        return data.data;
    }

    async create(
        InfoDeConsulta: string,
        Historico: string,
        HistoricoAlimentar: string,
        HistoricoGestacional: string,
        Observacoes: string
    ): Promise<consulta_infos> {
        const userStore = useUserStore();
        const body = new FormData();
        body.append(
            'data',
            JSON.stringify({
                InfoDeConsulta,
                Historico,
                HistoricoAlimentar,
                HistoricoGestacional,
                Observacoes,
            }),
        );

        const { data } = await api.post('/consulta-infos', body, {
            headers: {
                Authorization: `Bearer ${userStore.token}`,
            },
        });
        return data.data;
    }

    async delete(id: number): Promise<void> {
        const userStore = useUserStore();
        const { data } = await api.delete(`/consulta-infos/${id}`, {
            headers: {
                Authorization: `Bearer ${userStore.token}`,
            },
        });
        return data.data;
    }

    async update(
        id: number,
        InfoDeConsulta: string,
        Historico: string,
        HistoricoAlimentar: string,
        HistoricoGestacional: string,
        Observacoes: string
    ): Promise<consulta_infos> {
        const userStore = useUserStore();
        const body = new FormData();
        body.append(
            'data',
            JSON.stringify({
                id,
                InfoDeConsulta,
                Historico,
                HistoricoAlimentar,
                HistoricoGestacional,
                Observacoes,
            }),
        );

        const { data } = await api.put(`/consulta-infos/${id}`, body, {
            headers: {
                Authorization: `Bearer ${userStore.token}`,
            },
        });
        return data.data;
    }

}

export default new ConsultaInfosService();