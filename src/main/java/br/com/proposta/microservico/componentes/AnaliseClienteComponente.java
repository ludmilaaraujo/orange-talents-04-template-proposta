package br.com.proposta.microservico.componentes;

import br.com.proposta.microservico.analisefinanceira.AnaliseClientFeign;
import br.com.proposta.microservico.analisefinanceira.ResultadoDaAnalise;
import br.com.proposta.microservico.analisefinanceira.SolicitacaoAnaliseRequest;
import br.com.proposta.microservico.cartao.CartaoClientFeign;
import br.com.proposta.microservico.cartao.CartaoRequest;
import br.com.proposta.microservico.cartao.CartaoResponse;
import br.com.proposta.microservico.entidades.Cartao;
import br.com.proposta.microservico.entidades.Proposta;
import br.com.proposta.microservico.repository.PropostaRepository;
import br.com.proposta.microservico.response.PropostaResponse;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.logging.Logger;

import static br.com.proposta.microservico.analisefinanceira.Resultado.COM_RETRICAO;
import static br.com.proposta.microservico.response.ElegibilidadeProposta.ELEGIVEL;
import static br.com.proposta.microservico.response.ElegibilidadeProposta.NAO_ELEGIVEL;

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
