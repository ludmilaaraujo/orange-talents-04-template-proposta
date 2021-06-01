package br.com.proposta.microservico.response;

import java.net.URI;

public class BiometriaResponse {

    private String biometria;
    private URI url;


    public BiometriaResponse(String biometria, URI url) {
        this.biometria = biometria;
        this.url = url;
    }

    public String getBiometria() {
        return biometria;
    }

    public URI getUrl() {
        return url;
    }
}
