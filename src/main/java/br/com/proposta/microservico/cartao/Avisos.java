package br.com.proposta.microservico.cartao;

import java.time.LocalDateTime;

public class Avisos {
    private LocalDateTime validoAte;
    private String destino;

    public Avisos(LocalDateTime validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public LocalDateTime getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
