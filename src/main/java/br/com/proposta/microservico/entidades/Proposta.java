package br.com.proposta.microservico.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpfOuCnpj;
    private String email;
    private String nome;
    private String endereco;
    private Double salario;

    public Proposta(String cpfOuCnpj, String email,
                    String nome, String endereco, Double salario) {
        this.cpfOuCnpj = cpfOuCnpj;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    @Override
    public String toString() {

        return this.nome + " " + this.cpfOuCnpj;
    }

    public Long getId() {
        return id;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Double getSalario() {
        return salario;
    }
}
