package br.com.proposta.microservico.proposta.controllers;

import br.com.proposta.microservico.proposta.entidades.Proposta;
import br.com.proposta.microservico.proposta.repositorys.PropostaRepository;
import br.com.proposta.microservico.proposta.responses.PropostaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class PropostaDetalheController {

    @Autowired
    private PropostaRepository propostaRepository;

    @GetMapping(value = "/proposta/{id}")
    public PropostaResponse consultaProposta(@PathVariable("id") Long id,
                                               UriComponentsBuilder builder){
        Proposta proposta = propostaRepository.getOne(id);
        URI url = builder.path("/proposta/{id}").build(proposta.getId());
        return new PropostaResponse(proposta, url);
    }

}