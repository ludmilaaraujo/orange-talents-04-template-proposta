package br.com.proposta.microservico.request;

import br.com.proposta.microservico.entidades.AvisoViagem;
import br.com.proposta.microservico.repository.CartaoRepository;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class AvisoViagemRequest {

    @NotBlank @NotNull
    private String destinoViagem;
    @NotBlank @NotNull @Future
    private Date dataInicioViagem;
    @NotBlank @NotNull @Future
    private Date dataTerminoViagem;
    @NotBlank @NotNull
    private String userAgent;

    public AvisoViagemRequest(@NotBlank @NotNull String destinoViagem,
                              @NotBlank @NotNull @Future Date dataInicioViagem, @NotBlank @NotNull @Future Date dataTerminoViagem,
                              @NotBlank @NotNull String userAgent) {
        this.destinoViagem = destinoViagem;
        this.dataInicioViagem = dataInicioViagem;
        this.dataTerminoViagem = dataTerminoViagem;
        this.userAgent = userAgent;
    }

    public AvisoViagem convertToEntity(CartaoRepository cartaoRepository, Long idCartao){
       return new AvisoViagem(cartaoRepository.getOne(idCartao) , this.destinoViagem,
               this.dataInicioViagem, this.dataTerminoViagem, this.userAgent);
    }

    public Date getDataInicioViagem() {
        return dataInicioViagem;
    }

    public Date getDataTerminoViagem() {
        return dataTerminoViagem;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
