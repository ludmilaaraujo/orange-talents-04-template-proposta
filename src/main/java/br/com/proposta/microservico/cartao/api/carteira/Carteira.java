package br.com.proposta.microservico.cartao.api.carteira;

public enum Carteira {
    PAGSEGURO(1, "PAGSEGURO"),
    PAYPAL(2, "PAYPAL"),
    SAMSUNG_PAY(3, "SAMSUNG_PAY"),
    ITAU(4, "ITAU");

    private Integer codigo;
    private String nome;

    Carteira(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}
