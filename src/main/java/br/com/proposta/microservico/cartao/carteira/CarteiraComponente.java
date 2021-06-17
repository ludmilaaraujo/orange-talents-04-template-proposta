package br.com.proposta.microservico.cartao.carteira;

import br.com.proposta.microservico.cartao.CartaoClientFeign;
import br.com.proposta.microservico.entidades.Cartao;
import br.com.proposta.microservico.entidades.Proposta;
import br.com.proposta.microservico.repository.CartaoRepository;
import br.com.proposta.microservico.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class CarteiraComponente {
    @Autowired
    CartaoClientFeign cartaoClientFeign;
    @Autowired
    PropostaRepository propostaRepository;
    @Autowired
    CartaoRepository cartaoRepository;

    public Boolean associarCarteira(Cartao cartao, Carteira carteira, UriComponentsBuilder builder, URI url ) {
        Proposta proposta = propostaRepository.findByCartaoId(cartao.getId());
        url = builder.path("/proposta/{id}").build(proposta.getId());
        SolicitacaoInclusaoCarteira solicitacaoInclusaoCarteira
                = new SolicitacaoInclusaoCarteira(proposta.getEmail(), carteira.getNome());
        ResultadoCarteira resultadoCarteira = cartaoClientFeign.associaCarteira(cartao.getIdCartao(), solicitacaoInclusaoCarteira);
        if (resultadoCarteira.getResultado().equals("ASSOCIADA")) {
            cartao.setIdCarteira(resultadoCarteira.getId());
            cartao.setCarteiraAssociada(carteira);
            cartaoRepository.save(cartao);
            return true;
        } else {
            return false;
        }
    }
}
