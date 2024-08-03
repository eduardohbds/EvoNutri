import { api } from "./index";
import {type dado_antropometricos} from "../types"
import { useUserStore } from "@/stores/userStore";

/*
export type dado_antropometricos ={
    Peso: string;
    Altura: string;
    Diliocristal: string;
    Dsuprailiaca: string;
    DobraAbdominal: string;
    DCutaneaAxilarMedia: string;
    DobraCutaneaCoxa: string;
    DobraCutaneaPeitoral: string;
    DobraCutaneaSubescapular: string;
    DobraCutaneaSupraespinal: string;
    DCTricipita: string;
    PerimetroCintura: string;
    PerimetroQuadril: string;
    createdAt: Date;
    updatedAt: Date;
};*/
class DadoAntropometricoService {
    async create(
        Peso: string,
        Altura: string,
        Diliocristal: string,
        Dsuprailiaca: string,
        DobraAbdominal: string,
        DCutaneaAxilarMedia: string,
        DobraCutaneaCoxa: string,
        DobraCutaneaPeitoral: string,
        DobraCutaneaSubescapular: string,
        DobraCutaneaSupraespinal: string,
        DCTricipita: string,
        PerimetroCintura: string,
        PerimetroQuadril: string,
    ): Promise<dado_antropometricos> {
        const userStore = useUserStore();
        const body = new FormData();
        body.append(
            'data',
            JSON.stringify({
                Peso,
                Altura,
                Diliocristal,
                Dsuprailiaca,
                DobraAbdominal,
                DCutaneaAxilarMedia,
                DobraCutaneaCoxa,
                DobraCutaneaPeitoral,
                DobraCutaneaSubescapular,
                DobraCutaneaSupraespinal,
                DCTricipita,
                PerimetroCintura,
                PerimetroQuadril,
            }),
        );

        const { data } = await api.post('/dado-antropometricos', body, {
            headers: {
                Authorization: `Bearer ${userStore.token}`,
            },
        });
        return data.data;
    }

    async delete(id: number): Promise<void> {
        await api.delete(`/dado-antropometricos/${id}`);
    }

    async getAll(): Promise<dado_antropometricos[]> {
        const response = await api.get<{ data: dado_antropometricos[] }>('/dado-antropometricos');
        return response.data.data;
    }

    async getById(id: number): Promise<dado_antropometricos> {
        const response = await api.get<{ data: dado_antropometricos }>(`/dado-antropometricos/${id}`);
        return response.data.data;
    }

    async update(
        id: number,
        Peso: string,
        Altura: string,
        Diliocristal: string,
        Dsuprailiaca: string,
        DobraAbdominal: string,
        DCutaneaAxilarMedia: string,
        DobraCutaneaCoxa: string,
        DobraCutaneaPeitoral: string,
        DobraCutaneaSubescapular: string,
        DobraCutaneaSupraespinal: string,
        DCTricipita: string,
        PerimetroCintura: string,
        PerimetroQuadril: string,): Promise<dado_antropometricos> {
        const response = await api.put<dado_antropometricos>(`/dado-antropometricos/${id}`);
        return response.data;
    }
}

export default new DadoAntropometricoService();