package br.com.proposta.microservico.cartao.api.carteira;

import javax.validation.constraints.NotNull;

public class CarteiraRequest {
    @NotNull
    Carteira carteira;

    @Deprecated
    public CarteiraRequest(){
    }

    public CarteiraRequest(@NotNull Carteira carteira) {
        this.carteira = carteira;
    }

    public Carteira getCarteira() {
        return carteira;
    }
}
