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

    public PropostaResponse(Proposta proposta, URI uri) {
        this.cpfOuCnpj = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.url = uri;
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
}
