package br.com.proposta.microservico.response;

import br.com.proposta.microservico.entidades.Proposta;

import java.net.URI;

public class PropostaResponse {

    private String cpfOuCnpj;
    private String email;
    private String nome;
    private String endereco;
    private Double salario;
    private URI url;
    private ElegibilidadeProposta elegibilidadeProposta;
    private String idCartao;

    public PropostaResponse(Proposta proposta, URI url) {
        this.cpfOuCnpj = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.url = url;
        this.elegibilidadeProposta = proposta.getElegibilidadeProposta();
        this.idCartao = proposta.getCartao().getIdCartao();
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Double getSalario() {
        return salario;
    }

    public URI getUrl() {
        return url;
    }

    public String getIdCartao() {
        return idCartao;
    }

    public ElegibilidadeProposta getElegibilidadeProposta() {
        return elegibilidadeProposta;
    }
}
