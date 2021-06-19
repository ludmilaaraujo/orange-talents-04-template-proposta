package br.com.proposta.microservico.cartao.requests;

import br.com.proposta.microservico.cartao.entidades.Biometria;
import br.com.proposta.microservico.cartao.entidades.Cartao;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class BiometriaRequest {

    @NotBlank
    private String biometria;

    public BiometriaRequest() {
    }

    public BiometriaRequest(String biometriaCodificada) {
        this.biometria = biometriaCodificada;
    }

    public Biometria convertyToEntity(Cartao cartao) {
        byte[] biometriaDecode = Base64.getDecoder().decode(this.biometria.getBytes());
        return new Biometria(biometria, cartao);
    }

    public String getBiometria() {
        return biometria;
    }
}
