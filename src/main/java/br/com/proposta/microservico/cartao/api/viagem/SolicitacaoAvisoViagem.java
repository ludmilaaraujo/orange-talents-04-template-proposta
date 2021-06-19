package br.com.proposta.microservico.cartao.api.viagem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SolicitacaoAvisoViagem {


    String padraoConversaoString = "yyyy-MM-dd";
    SimpleDateFormat conversorDataPadraoAmericano = new SimpleDateFormat(padraoConversaoString);


    private String destino;
    private String validoAte;

    public SolicitacaoAvisoViagem(String destino, Date validoAte) {
        this.destino = destino;
        this.validoAte = conversorDataPadraoAmericano.format(validoAte);
    }

    public String getDestino() {
        return destino;
    }

    public String getValidoAte() {
        return validoAte;
    }
}
