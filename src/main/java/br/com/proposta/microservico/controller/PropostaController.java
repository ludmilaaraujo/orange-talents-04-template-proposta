package br.com.proposta.microservico.controller;

import br.com.proposta.microservico.entidades.Proposta;
import br.com.proposta.microservico.repository.PropostaRepository;
import br.com.proposta.microservico.request.PropostaRequest;
import br.com.proposta.microservico.response.PropostaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping(value = "/proposta")
    @Transactional
    public ResponseEntity<PropostaResponse> criaProposta(@RequestBody @Valid PropostaRequest propostaRequest,
                                                         UriComponentsBuilder urlBuilder){
        Proposta novaProposta = propostaRequest.convertToEntity();
        propostaRepository.save(novaProposta);
        URI urlAceso = urlBuilder.path("/proposta/{id}").build(novaProposta.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(new PropostaResponse(novaProposta, urlAceso));
    }
}
