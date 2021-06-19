package br.com.proposta.microservico.cartao.entidades;

import br.com.proposta.microservico.cartao.api.carteira.Carteira;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idCartao;
    private String idCarteira;
    private LocalDateTime emitidoEm;
    private String titular;
    private String idVencimento;
    private Integer diaVencimento;
    private Carteira carteiraAssociada;

    public Cartao(String idCartao, LocalDateTime emitidoEm,
                  String titular, String idVencimento, Integer diaVencimento) {

        this.idCartao = idCartao;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.idVencimento = idVencimento;
        this.diaVencimento = diaVencimento;
    }

    public Cartao(){

    }

    public Carteira getCarteiraAssociada() {
        return carteiraAssociada;
    }

    public void setCarteiraAssociada(Carteira carteiraAssociada) {
        this.carteiraAssociada = carteiraAssociada;
    }

    public String getIdCarteira() {
        return idCarteira;
    }

    public void setIdCarteira(String idCarteira) {
        this.idCarteira = idCarteira;
    }

    public Long getId() {
        return id;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public String getIdVencimento() {
        return idVencimento;
    }

    public Integer getDiaVencimento() {
        return diaVencimento;
    }
}
