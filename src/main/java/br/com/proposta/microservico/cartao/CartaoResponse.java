package br.com.proposta.microservico.cartao;

import java.time.LocalDateTime;
import java.util.List;

public class CartaoResponse {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<BloqueioResponse> bloqueios;
    private List<Avisos> avisos;
    private List<Carteiras> carteiras;
    private List<Parcelas> parcelas;
    private Double limite;
    private Renegociacao renegociacao;
    private Vencimento vencimento;
    private String idProposta;

    public CartaoResponse(String id, LocalDateTime emitidoEm, String titular, List<BloqueioResponse> bloqueios,
                          List<Avisos> avisos, List<Carteiras> carteiras, List<Parcelas> parcelas, Double limite,
                          Renegociacao renegociacao, Vencimento vencimento, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<BloqueioResponse> getBloqueios() {
        return bloqueios;
    }

    public List<Avisos> getAvisos() {
        return avisos;
    }

    public List<Carteiras> getCarteiras() {
        return carteiras;
    }

    public List<Parcelas> getParcelas() {
        return parcelas;
    }

    public Double getLimite() {
        return limite;
    }

    public Renegociacao getRenegociacao() {
        return renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
