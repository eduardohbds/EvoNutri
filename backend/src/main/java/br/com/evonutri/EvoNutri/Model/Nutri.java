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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "nutricionista")
@Data
@Builder
public class Nutri {

    
    private String id; // O Id é o CPF

    private String name;
    private String crnOrCrm;

    @Pattern(regexp = "^\\+?[0-9\\s.-]{7,15}$")
    private String contactNumber;
    @Pattern(regexp = "^(1[01][0-9]|120|[1-9]?[0-9])$")
    private String age;
    @Email
    @NotNull
    private String email;
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|\\d{11}$")
    private String cpf;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // Changed to @OneToMany
    @JoinColumn(name = "nutri_id") // Renamed to match with the `Nutri` entity
    private List<Cliente> clientes;

    @ElementCollection
    @CollectionTable(name = "horarios_disponiveis", joinColumns = @JoinColumn(name = "nutri_id"))
    @Column(name = "horario")
    private List<String> horariosDisponiveis;
}