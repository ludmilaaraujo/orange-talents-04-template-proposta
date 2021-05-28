package br.com.proposta.microservico.controller;

import br.com.proposta.microservico.entidades.Proposta;
import br.com.proposta.microservico.repository.PropostaRepository;
import br.com.proposta.microservico.response.ElegibilidadeProposta;
import br.com.proposta.microservico.response.PropostaResponse;
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