package br.com.proposta.microservico.entidades;

import br.com.proposta.microservico.response.ElegibilidadeProposta;

import javax.persistence.*;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private Double salario;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cartao cartao;
    private ElegibilidadeProposta elegibilidadeProposta;

    public Proposta(String documento, String email, String nome, String endereco,
                    Double salario, Cartao cartao, ElegibilidadeProposta elegibilidadeProposta) {
        this.id = id;
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.cartao = cartao;
        this.elegibilidadeProposta = elegibilidadeProposta;
    }

    public Proposta(){

    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
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

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public ElegibilidadeProposta getElegibilidadeProposta() {
        return elegibilidadeProposta;
    }

    public void setElegibilidadeProposta(ElegibilidadeProposta elegibilidadeProposta) {
        this.elegibilidadeProposta = elegibilidadeProposta;
    }
}
