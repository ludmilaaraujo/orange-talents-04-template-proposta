package br.com.proposta.microservico.cartao.controllers;

import br.com.proposta.microservico.cartao.api.BloqueioRequest;
import br.com.proposta.microservico.cartao.api.CartaoClientFeign;
import br.com.proposta.microservico.cartao.api.ResultadoBloqueio;
import br.com.proposta.microservico.cartao.entidades.Cartao;
import br.com.proposta.microservico.cartao.repositorys.BloqueioCartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
public class BloqueioCartaoController {

    @Autowired
    private BloqueioCartaoRepository bloqueioCartaoRepository;

    @Autowired
    private CartaoClientFeign cartaoClientFeign;

    @PutMapping(value = "/bloqueioCartao/{idCartao}")
    @Transactional
    public ResponseEntity<?> bloqueiaCartao(@PathVariable Long idCartao){

        Optional<Cartao> cartao = bloqueioCartaoRepository.findById(idCartao);
        if(cartao.isPresent()){
            ResultadoBloqueio resultadoBloqueio =
                    cartaoClientFeign.bloqueiaCartao(cartao.get().getIdCartao(),
                            new BloqueioRequest("proposta"));
            if (resultadoBloqueio.equals(ResultadoBloqueio.BLOQUEADO)){
                return ResponseEntity.status(HttpStatus.CREATED).body("O cartão foi " +
                        "bloqueado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O cartão não foi " +
                        "bloqueado");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O cartão não foi " +
                "encontrado");
    }
}
