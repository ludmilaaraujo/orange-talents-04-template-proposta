package br.com.proposta.microservico.cartao;

import br.com.proposta.microservico.cartao.carteira.ResultadoCarteira;
import br.com.proposta.microservico.cartao.carteira.SolicitacaoInclusaoCarteira;
import br.com.proposta.microservico.cartao.viagem.ResultadoAvisoViagem;
import br.com.proposta.microservico.cartao.viagem.SolicitacaoAvisoViagem;
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

    @PostMapping("/api/cartoes/{idCartao}/avisos")
    ResultadoAvisoViagem avisoViagem(@PathVariable(name = "idCartao") String idCartao,
                                     @RequestBody SolicitacaoAvisoViagem solicitacaoAvisoViagem);

    @PostMapping("/api/cartoes/{idCartao}/carteiras")
    ResultadoCarteira associaCarteira(@PathVariable(name = "idCartao") String idCartao,
                             @RequestBody SolicitacaoInclusaoCarteira solicitacaoInclusaoCarteira);
}
