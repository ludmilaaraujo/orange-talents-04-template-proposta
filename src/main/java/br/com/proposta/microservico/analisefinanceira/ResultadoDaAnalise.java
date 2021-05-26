package br.com.proposta.microservico.analisefinanceira;

public class ResultadoDaAnalise {
    private String documento;
    private String nome;
    private Resultado resultadoSolicitacao;
    private String idProposta;

    public ResultadoDaAnalise(String documento, String nome, Resultado resultadoSolicitacao, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Resultado getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
