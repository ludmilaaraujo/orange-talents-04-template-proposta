package br.com.proposta.microservico.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="analise-solicitacao", url="${analise.host}")
public interface AnaliseClient {

    @PostMapping("/api/solicitacao")
    ResultadoDaAnalise consultaAnalise(SolicitacaoAnaliseRequest request);
}
