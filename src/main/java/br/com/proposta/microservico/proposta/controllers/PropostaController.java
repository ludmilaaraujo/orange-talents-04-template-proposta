package br.com.proposta.microservico.proposta.controllers;

import br.com.proposta.microservico.proposta.componentes.AnaliseClienteComponente;
import br.com.proposta.microservico.proposta.entidades.Proposta;
import br.com.proposta.microservico.proposta.repositorys.PropostaRepository;
import br.com.proposta.microservico.proposta.requests.PropostaRequest;
import br.com.proposta.microservico.proposta.responses.PropostaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private AnaliseClienteComponente analiseClienteComponente;


    @PostMapping(value = "/proposta")
    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<PropostaResponse> criaProposta(@RequestBody @Valid PropostaRequest propostaRequest,
                                                         UriComponentsBuilder urlBuilder) throws Exception {
        Proposta propostaCadastrada = propostaRequest.convertToEntity();
        propostaRepository.save(propostaCadastrada);
        PropostaResponse propostaResponse =
                analiseClienteComponente.analisaProposta(propostaCadastrada, urlBuilder);
        return ResponseEntity.status(HttpStatus.CREATED).body(propostaResponse);
    }
}
