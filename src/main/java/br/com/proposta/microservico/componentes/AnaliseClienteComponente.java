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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static br.com.proposta.microservico.analisefinanceira.Resultado.COM_RETRICAO;
import static br.com.proposta.microservico.response.ElegibilidadeProposta.ELEGIVEL;
import static br.com.proposta.microservico.response.ElegibilidadeProposta.NAO_ELEGIVEL;

@Component
public class AnaliseClienteComponente {

    @Autowired
    private AnaliseClientFeign analiseClientFeign;

    @Autowired
    private CartaoClientFeign cartaoClientFeign;

    @Autowired
    private PropostaRepository propostaRepository;

    public PropostaResponse analisaProposta(Proposta proposta, UriComponentsBuilder builder) {
        ResultadoDaAnalise resultadoDaAnalise = null;
        URI url = builder.path("/proposta/{id}").build(proposta.getId());
        SolicitacaoAnaliseRequest solicitacaoAnaliseRequest = new SolicitacaoAnaliseRequest
                (proposta);
        try {
            resultadoDaAnalise = analiseClientFeign.consultaAnalise(solicitacaoAnaliseRequest);
            if(resultadoDaAnalise.getResultadoSolicitacao().equals(COM_RETRICAO)){
                return new PropostaResponse(proposta, url, NAO_ELEGIVEL);
            } else {
                emiteCartao(proposta);
                return new PropostaResponse(proposta, url, ELEGIVEL);
            }
        } catch (Exception e){
            System.out.println("Erro ao tentar comunicar com a API de Analise");
        }
        return null;

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
        } catch (Exception e){
            System.out.println("Erro ao tentar comunicar com a API de Cartão");
        }
        proposta.setCartao(cartao);
        propostaRepository.saveAndFlush(proposta);
        return cartao;
    }
}
