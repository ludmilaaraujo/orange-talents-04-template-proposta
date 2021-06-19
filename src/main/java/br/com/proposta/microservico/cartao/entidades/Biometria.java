package br.com.proposta.microservico.cartao.entidades;

import javax.persistence.*;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String biometria;
    @ManyToOne
    private Cartao cartao;

    public Biometria(String biometria, Cartao cartao) {
        this.biometria = biometria;
        this.cartao = cartao;
    }

    public String getBiometria() {
        return biometria;
    }


    public Cartao getCartao() {
        return cartao;
    }

    public Long getId() {
        return id;
    }
}
