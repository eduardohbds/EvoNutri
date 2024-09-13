package br.com.evonutri.EvoNutri.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "clientes")
@Data
@Builder
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String endereco;
    @Pattern(regexp = "^\\+?[0-9\\s.-]{7,15}$\n")
    private String phone;
    @Pattern(regexp = "^(1[01][0-9]|120|[1-9]?[0-9])$")
    private String age;
    private double weight;
    @Email
    @NotNull
    private String email;
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|\\d{11}$")
    private String cpf;
}