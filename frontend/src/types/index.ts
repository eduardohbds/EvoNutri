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
};

export type dado_laboratorials = { 
    CHDL: string;
    CLDL: string;
    ColesterolTotal: string;
    PressaoArtDistolica: string;
    PressaoArtSistolica: string;
    Trigliceridios: string;
};

export type consulta_infos = {
    id:number;
    InfoDeConsulta: string;
    Historico: string;
    HistoricoAlimentar: string;
    HistoricoGestacional: string;
    Observacoes: string;
};

export type User = {
    username: string;
    email:string;
    provider: string;
    password:string;
    resetPasswordToken:string;
    confirmationToken:string;
    confirmed:Boolean;
    blocked:Boolean;
    role: string;
    crmn:string;
    jwt:string;
};
