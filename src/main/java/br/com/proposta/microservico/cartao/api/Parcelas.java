package br.com.proposta.microservico.cartao.api;

public class Parcelas {
    private String id;
    private Integer quantidade;
    private Double valor;

    public Parcelas(String id, Integer quantidade, Double valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
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
}
