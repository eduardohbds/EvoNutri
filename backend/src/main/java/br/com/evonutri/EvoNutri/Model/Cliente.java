package br.com.evonutri.EvoNutri.Model;

public class Cliente {

    private String name;
    private String endereco;
    private String phone;
    private String age;
    private double weight;
    private String email;
    private String cpf;
    
    public Cliente(String name, String endereco, String phone, String age, double weight, String email, String cpf) {
        this.name = name;
        this.endereco = endereco;
        this.phone = phone;
        this.age = age;
        this.weight = weight;
        this.email = email;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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

    @Override
    public String toString() {
        return ("Cliente [getName()=" + getName() + ", getEndereco()=" + getEndereco() + ", getPhone()=" + getPhone() + ", getAge()=" + getAge() + ", getWeight()=" + getWeight() + ", getEmail()=" + getEmail()   + ", getCpf()=" + getCpf() + ", getClass()=" + getClass() + "]");
    }

}