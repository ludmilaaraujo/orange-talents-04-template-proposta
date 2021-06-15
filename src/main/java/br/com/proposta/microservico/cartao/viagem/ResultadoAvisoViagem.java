package br.com.proposta.microservico.cartao.viagem;

public class ResultadoAvisoViagem {
    private String resultado;

    @Deprecated
    public ResultadoAvisoViagem(){

    }
    public ResultadoAvisoViagem(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
