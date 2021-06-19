package br.com.proposta.microservico.cartao.controllers;

import br.com.proposta.microservico.cartao.api.carteira.CarteiraComponente;
import br.com.proposta.microservico.cartao.api.carteira.CarteiraRequest;
import br.com.proposta.microservico.cartao.entidades.Cartao;
import br.com.proposta.microservico.cartao.repositorys.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class CarteiraController {

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    CarteiraComponente carteiraComponente;

    @PutMapping(value = "/carteira/{idCartao}")
    public ResponseEntity<?> associaCarteira(@PathVariable Long idCartao,
                                             @Valid @RequestBody CarteiraRequest carteiraRequest,
                                             UriComponentsBuilder urlBuilder) {
        URI url = urlBuilder.build().toUri();
        Optional<Cartao> cartaoOptional = cartaoRepository.findById(idCartao);
        if (cartaoOptional.isPresent()) {
            Cartao cartao = cartaoOptional.get();
            if (cartao.getCarteiraAssociada() != null && cartao.getCarteiraAssociada()
                    .equals(carteiraRequest.getCarteira())) {
                return carteiraExistente();
            }
            if (carteiraComponente.associarCarteira(cartao, carteiraRequest.getCarteira(), urlBuilder, url)) {
                return cateiraAssociadaComSucesso(url);
            } else {
                return carteiraNaoAssociada();
            }
        }
        return cartaoNaoExistente();


    }

    private ResponseEntity<String> cartaoNaoExistente() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O cartão não foi" +
                "encontrado");
    }

    private ResponseEntity carteiraNaoAssociada() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O cartão não" +
        "foi associado.");
    }

    private ResponseEntity cateiraAssociadaComSucesso(URI uri) {
        return ResponseEntity.created(uri).body("O cartão foi " +
         "associado com sucesso");
    }

    private ResponseEntity<?> carteiraExistente() {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Já existe " +
                "esse cartão associado com esta carteira.");
    }
}
