package br.com.evonutri.EvoNutri.Model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
@Entity
@Table(name = "dieta")
@Data
@Builder
public class Dieta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id; // ID único auto gerado
    @NotNull
    private String descricao;
    private Nutri nutri; // Nutricionista que gerou a dieta
    private Cliente cliente; // Cliente a quem a dieta é atribuída
    
    private String dataInicio;
    
    private String dataFim;
    private int qtdRef; // Quantidade de refeições
    private List<Meals> refeicoes; // Lista de refeições na dieta

}