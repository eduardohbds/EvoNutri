package br.com.evonutri.EvoNutri.Model;

import java.util.List;
import java.util.UUID;

public class Dieta {

    private String id; // ID único auto gerado
    private String descricao;
    private Nutri nutri; // Nutricionista que gerou a dieta
    private Cliente cliente; // Cliente a quem a dieta é atribuída
    private String dataInicio;
    private String dataFim;
    private int qtdRef; // Quantidade de refeições
    private List<Meals> refeicoes; // Lista de refeições na dieta

    public Dieta(String descricao, Nutri nutri, Cliente cliente, String dataInicio, String dataFim, int qtdRef, List<Meals> refeicoes) {
        this.id = UUID.randomUUID().toString(); // Gera um ID único
        this.descricao = descricao;
        this.nutri = nutri;
        this.cliente = cliente;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.qtdRef = qtdRef;
        this.refeicoes = refeicoes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Nutri getNutri() {
        return nutri;
    }

    public void setNutri(Nutri nutri) {
        this.nutri = nutri;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public int getQtdRef() {
        return qtdRef;
    }

    public void setQtdRef(int qtdRef) {
        this.qtdRef = qtdRef;
    }

    public List<Meals> getRefeicoes() {
        return refeicoes;
    }

    public void setRefeicoes(List<Meals> refeicoes) {
        this.refeicoes = refeicoes;
    }

    @Override
    public String toString() {
        return "Dieta [id=" + id + ", descricao=" + descricao + ", nutri=" + nutri.getName() + 
               ", cliente=" + cliente.getName() + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + 
               ", qtdRef=" + qtdRef + ", refeicoes=" + refeicoes + "]";
    }
}