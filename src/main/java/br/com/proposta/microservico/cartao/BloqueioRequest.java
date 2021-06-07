package br.com.proposta.microservico.cartao;

public class BloqueioRequest {
    private String sistemaResponsavel;

    public BloqueioRequest() {
    }

    public BloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public void setSistemaResponsavel(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }
}
