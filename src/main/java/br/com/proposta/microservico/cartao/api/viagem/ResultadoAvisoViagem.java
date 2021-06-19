package br.com.proposta.microservico.cartao.api.viagem;

public class ResultadoAvisoViagem {
    private String resultado;
    private String id;

    public ResultadoAvisoViagem(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
