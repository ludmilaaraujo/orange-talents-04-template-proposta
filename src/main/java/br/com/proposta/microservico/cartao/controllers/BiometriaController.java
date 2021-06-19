package br.com.proposta.microservico.cartao.controllers;

import br.com.proposta.microservico.cartao.entidades.Biometria;
import br.com.proposta.microservico.cartao.entidades.Cartao;
import br.com.proposta.microservico.cartao.repositorys.BiometriaRepository;
import br.com.proposta.microservico.cartao.repositorys.CartaoRepository;
import br.com.proposta.microservico.cartao.requests.BiometriaRequest;
import br.com.proposta.microservico.common.config.handler.ErrosFormularioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class BiometriaController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BiometriaRepository biometriaRepository;

    @Transactional
    @PostMapping(value = "/biometria/{idCartao}/cadastrar")
    public ResponseEntity<?> criaBiometria(@RequestBody
                                           @Valid BiometriaRequest request,
                                           @PathVariable Long idCartao,
                                           UriComponentsBuilder uriBuilder) {

        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
        if (!cartao.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrosFormularioDTO("cartão", "O cartão informado não existe!"));
        }

        Biometria biometria = request.convertyToEntity(cartao.get());
        biometriaRepository.save(biometria);
        URI uri = uriBuilder.path("/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
