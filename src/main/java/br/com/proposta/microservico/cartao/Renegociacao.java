package br.com.proposta.microservico.cartao;

import java.time.LocalDateTime;

public class Renegociacao {

    private String id;
    private Integer quantidade;
    private Double valor;
    private LocalDateTime dataDeCriacao;

    public Renegociacao(String id, Integer quantidade,
                        Double valor, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
