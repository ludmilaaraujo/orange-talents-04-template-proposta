package br.com.proposta.microservico.cartao;

import br.com.proposta.microservico.analisefinanceira.ResultadoDaAnalise;
import br.com.proposta.microservico.analisefinanceira.SolicitacaoAnaliseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="cartao", url="${cartoes.host}")
public interface CartaoClientFeign {

    @GetMapping("/api/cartoes?idProposta")
    CartaoResponse consultaCartao(@RequestParam(name="idProposta") String idProposta);

    @PostMapping("/api/cartoes")
    CartaoResponse cadastraCartao(CartaoRequest cartaoRequest);

    @PostMapping("/api/cartoes/{idCartao}/bloqueios")
    ResultadoBloqueio bloqueiaCartao(@PathVariable(name = "idCartao") String idCartao,
                                     @RequestBody BloqueioRequest bloqueioRequest);
}
