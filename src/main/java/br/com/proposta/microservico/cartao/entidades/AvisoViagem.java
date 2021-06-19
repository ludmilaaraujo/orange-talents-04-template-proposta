package br.com.proposta.microservico.cartao.entidades;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class AvisoViagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cartao cartao;
    private String destinoViagem;
    private Date dataInicioViagem;
    private Date dataTerminoViagem;
    private LocalDateTime instanteCriacaoAvisoViagem;
    private String ipCliente;
    private String userAgent;

    public AvisoViagem(Cartao cartao, String destinoViagem, Date dataInicioViagem, Date dataTerminoViagem,
                       String userAgent) {
        this.cartao = cartao;
        this.destinoViagem = destinoViagem;
        this.dataInicioViagem = dataInicioViagem;
        this.dataTerminoViagem = dataTerminoViagem;
        this.instanteCriacaoAvisoViagem = LocalDateTime.now();
        this.userAgent = userAgent;
    }


    public void setIpCliente(String ipCliente) {
        this.ipCliente = ipCliente;
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public Date getDataInicioViagem() {
        return dataInicioViagem;
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

    public String respostaAvisoViagem(){
        return ("Cliente Ip : " + getUserAgent() + " estará de férias entre " +
                getDataInicioViagem() + " e " + getDataTerminoViagem());
    }
}
