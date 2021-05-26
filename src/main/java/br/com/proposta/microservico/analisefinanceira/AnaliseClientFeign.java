package br.com.proposta.microservico.analisefinanceira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="analise-solicitacao", url="${analise.host}")
public interface AnaliseClientFeign {

    @PostMapping("/api/solicitacao")
    ResultadoDaAnalise consultaAnalise(SolicitacaoAnaliseRequest request);
}
