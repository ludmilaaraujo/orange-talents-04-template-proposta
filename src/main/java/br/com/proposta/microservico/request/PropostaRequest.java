package br.com.proposta.microservico.request;

import br.com.proposta.microservico.anotacoes.AtributoUnico;
import br.com.proposta.microservico.entidades.Proposta;
import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PropostaRequest {

    @NotNull
    @NotBlank
    @Size(min = 11, max = 14)
    @AtributoUnico(fieldName = "documento", domainClass = Proposta.class)
    private String documento;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    private String endereco;
    @NotNull
    @Positive
    private Double salario;

    public PropostaRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
                           @NotBlank String endereco, @NotBlank @Positive Double salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta convertToEntity() {
        return new Proposta(this.documento,
                this.nome, this.email, this.endereco, this.salario);
    }
}
