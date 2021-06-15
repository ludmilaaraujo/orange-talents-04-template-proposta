package br.com.proposta.microservico.controller;

import br.com.proposta.microservico.entidades.Cartao;
import br.com.proposta.microservico.repository.CartaoRepository;
import br.com.proposta.microservico.request.AvisoViagemRequest;
import br.com.proposta.microservico.cartao.viagem.ViagemComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AvisoViagemController {

    @Autowired
    private ViagemComponent viagemComponent;

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping(value = "/avisoViagem/{idCartao}")
    @Transactional
    public ResponseEntity<?> avisoViagem(@Valid @RequestBody AvisoViagemRequest avisoViagemRequest,
                                         @PathVariable Long idCartao,
                                         UriComponentsBuilder uriBuilder,
                                         HttpServletRequest request) throws Exception {
        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
        if (cartao.isPresent()) {
            viagemComponent.registrarNovaViagem(avisoViagemRequest, idCartao, request);
            return ResponseEntity.status(HttpStatus.OK).body("O aviso foi " +
                    "criado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O cartão não existe");
        }
    }
}
