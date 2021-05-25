package br.com.proposta.microservico.client;

import br.com.proposta.microservico.entidades.Proposta;

public class SolicitacaoAnaliseRequest {
    private String documento;
    private String nome;
    private String idProposta;

    public SolicitacaoAnaliseRequest(Proposta proposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
