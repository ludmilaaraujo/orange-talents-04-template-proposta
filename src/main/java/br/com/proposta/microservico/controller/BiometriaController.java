package br.com.proposta.microservico.controller;

import br.com.proposta.microservico.entidades.Biometria;
import br.com.proposta.microservico.entidades.Cartao;
import br.com.proposta.microservico.handler.ErrosFormularioDTO;
import br.com.proposta.microservico.repository.BiometriaRepository;
import br.com.proposta.microservico.repository.CartaoRepository;
import br.com.proposta.microservico.request.BiometriaRequest;
import br.com.proposta.microservico.response.BiometriaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
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
