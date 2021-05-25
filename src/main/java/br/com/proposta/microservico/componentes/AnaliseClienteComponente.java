package br.com.proposta.microservico.componentes;

import br.com.proposta.microservico.client.AnaliseClient;
import br.com.proposta.microservico.client.ResultadoDaAnalise;
import br.com.proposta.microservico.client.SolicitacaoAnaliseRequest;
import br.com.proposta.microservico.entidades.Proposta;
import br.com.proposta.microservico.response.PropostaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static br.com.proposta.microservico.client.Resultado.COM_RETRICAO;
import static br.com.proposta.microservico.response.ElegibilidadeProposta.ELEGIVEL;
import static br.com.proposta.microservico.response.ElegibilidadeProposta.NAO_ELEGIVEL;

@Component
public class AnaliseClienteComponente {

    @Autowired
    private AnaliseClient analiseClient;

    public PropostaResponse analisaProposta(Proposta proposta, UriComponentsBuilder builder) {
        ResultadoDaAnalise resultadoDaAnalise = null;
        URI url = builder.path("/proposta/{id}").build(proposta.getId());
        SolicitacaoAnaliseRequest solicitacaoAnaliseRequest = new SolicitacaoAnaliseRequest
                (proposta);
        try {
            resultadoDaAnalise = analiseClient.consultaAnalise(solicitacaoAnaliseRequest);
            if(resultadoDaAnalise.getResultadoSolicitacao().equals(COM_RETRICAO)){
                return new PropostaResponse(proposta, url, NAO_ELEGIVEL);
            } else {
                return new PropostaResponse(proposta, url, ELEGIVEL);
            }
        } catch (Exception e){
            System.out.println("Erro ao tentar comunicar com a API");
        }
        return null;

    }
}
