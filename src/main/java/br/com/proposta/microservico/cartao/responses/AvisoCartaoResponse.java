package br.com.proposta.microservico.cartao.responses;

import java.time.LocalDateTime;
import java.util.Date;

public class AvisoCartaoResponse {

    private String identificadorCartao;
    private String destinoViagem;
    private Date dataTerminoViagem;
    private LocalDateTime instanteCriacaoAvisoViagem;
    private String ipCliente;
    private String userAgent;

    public AvisoCartaoResponse(String identificadorCartao, String destinoViagem, Date dataTerminoViagem,
                               LocalDateTime instanteCriacaoAvisoViagem, String ipCliente, String userAgent) {
        this.identificadorCartao = identificadorCartao;
        this.destinoViagem = destinoViagem;
        this.dataTerminoViagem = dataTerminoViagem;
        this.instanteCriacaoAvisoViagem = instanteCriacaoAvisoViagem;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
    }

    public String getIdentificadorCartao() {
        return identificadorCartao;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public Date getDataTerminoViagem() {
        return dataTerminoViagem;
    }

    public LocalDateTime getInstanteCriacaoAvisoViagem() {
        return instanteCriacaoAvisoViagem;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
