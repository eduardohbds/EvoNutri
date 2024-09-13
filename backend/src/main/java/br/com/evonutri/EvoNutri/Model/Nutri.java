package br.com.evonutri.EvoNutri.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "nutricionista")
@Data
@Builder
public class Nutri {

    
    private String id; // O Id é o CPF

    private String name;
    private String contactNumber;
    private String email;
    private String cpf;
    private String crnOrCrm;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // Changed to @OneToMany
    @JoinColumn(name = "nutri_id") // Renamed to match with the `Nutri` entity
    private List<Cliente> clientes;

    @ElementCollection
    @CollectionTable(name = "horarios_disponiveis", joinColumns = @JoinColumn(name = "nutri_id"))
    @Column(name = "horario")
    private List<String> horariosDisponiveis;
}