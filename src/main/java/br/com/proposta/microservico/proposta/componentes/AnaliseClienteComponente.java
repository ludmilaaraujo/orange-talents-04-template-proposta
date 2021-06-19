package br.com.proposta.microservico.proposta.componentes;

import br.com.proposta.microservico.cartao.api.CartaoClientFeign;
import br.com.proposta.microservico.cartao.api.CartaoRequest;
import br.com.proposta.microservico.cartao.api.CartaoResponse;
import br.com.proposta.microservico.cartao.entidades.Cartao;
import br.com.proposta.microservico.proposta.analisefinanceira.AnaliseClientFeign;
import br.com.proposta.microservico.proposta.analisefinanceira.ResultadoDaAnalise;
import br.com.proposta.microservico.proposta.analisefinanceira.SolicitacaoAnaliseRequest;
import br.com.proposta.microservico.proposta.entidades.Proposta;
import br.com.proposta.microservico.proposta.repositorys.PropostaRepository;
import br.com.proposta.microservico.proposta.responses.PropostaResponse;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static br.com.proposta.microservico.proposta.analisefinanceira.Resultado.COM_RETRICAO;
import static br.com.proposta.microservico.proposta.responses.ElegibilidadeProposta.ELEGIVEL;
import static br.com.proposta.microservico.proposta.responses.ElegibilidadeProposta.NAO_ELEGIVEL;

@Component
@Slf4j
public class AnaliseClienteComponente {

    @Autowired
    private AnaliseClientFeign analiseClientFeign;

    @Autowired
    private CartaoClientFeign cartaoClientFeign;

    @Autowired
    private PropostaRepository propostaRepository;

    public PropostaResponse analisaProposta(Proposta proposta, UriComponentsBuilder builder) throws Exception {
        log.info("Preparando envio da requisicao da analise");
        ResultadoDaAnalise resultadoDaAnalise = null;
        URI url = builder.path("/proposta/{id}").build(proposta.getId());
        SolicitacaoAnaliseRequest solicitacaoAnaliseRequest = new SolicitacaoAnaliseRequest
                (proposta);
        log.info("Dados do Envio idProposta: " + solicitacaoAnaliseRequest.getIdProposta());
        log.info("Dados do Envio documento: " + solicitacaoAnaliseRequest.getDocumento());
        log.info("Dados do Envio nome: " + solicitacaoAnaliseRequest.getNome());
        resultadoDaAnalise = analiseClientFeign.consultaAnalise(solicitacaoAnaliseRequest);
        if(resultadoDaAnalise.getResultadoSolicitacao().equals(COM_RETRICAO)){
            proposta.setElegibilidadeProposta(NAO_ELEGIVEL);
            propostaRepository.save(proposta);
            return new PropostaResponse(proposta, url);

        } else {
            proposta.setElegibilidadeProposta(ELEGIVEL);
            propostaRepository.save(proposta);
            emiteCartao(proposta);
            return new PropostaResponse(proposta, url);
        }

    }

    public Cartao emiteCartao(Proposta proposta) {
        Cartao cartao = null;
        CartaoResponse cartaoResponse = null;
        CartaoRequest cartaoRequest = new CartaoRequest(proposta.getDocumento(),
                proposta.getNome(), proposta.getId().toString());
        try {
            cartaoResponse = cartaoClientFeign.cadastraCartao(cartaoRequest);
            cartao = new Cartao(cartaoResponse.getId(), cartaoResponse.getEmitidoEm(),
                    cartaoResponse.getTitular(), cartaoResponse.getVencimento().getId(),
                    cartaoResponse.getVencimento().getDia());
        }  catch (FeignException e){
            log.error("Erro ao tentar comunicar com a API de Cartão", e.getCause());
        }
        proposta.setCartao(cartao);
        propostaRepository.saveAndFlush(proposta);
        return cartao;
    }
}
