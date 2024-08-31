package br.com.evonutri.EvoNutri.Model;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Nutri {

    private String id; 
    private String name;
    private String contactNumber;
    private String email;
    private String cpf;
    private String crnOrCrm;
    private List<Cliente> clientes; 
    private List<String> horariosDisponiveis; 

    public Nutri(String name, String contactNumber, String email, String cpf, String crnOrCrm) {
        this.id = UUID.randomUUID().toString(); // Gera um ID único
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.cpf = cpf;
        this.crnOrCrm = crnOrCrm;
        this.clientes = new ArrayList<>();
        this.horariosDisponiveis = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCrnOrCrm() {
        return crnOrCrm;
    }

    public void setCrnOrCrm(String crnOrCrm) {
        this.crnOrCrm = crnOrCrm;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public List<String> getHorariosDisponiveis() {
        return horariosDisponiveis;
    }

    public void setHorariosDisponiveis(List<String> horariosDisponiveis) {
        this.horariosDisponiveis = horariosDisponiveis;
    }

    public void addHorarioDisponivel(String horario) {
        this.horariosDisponiveis.add(horario);
    }

    @Override
    public String toString() {
        return "Nutricionista [id=" + id + ", name=" + name + ", contactNumber=" + contactNumber + 
               ", email=" + email + ", cpf=" + cpf + ", crnOrCrm=" + crnOrCrm + 
               ", clientes=" + clientes + ", horariosDisponiveis=" + horariosDisponiveis + "]";
    }

    public void setClientes(List<Cliente> clientes2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setClientes'");
    }
}
