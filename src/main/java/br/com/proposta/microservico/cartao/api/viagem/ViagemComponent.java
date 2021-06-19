package br.com.proposta.microservico.cartao.api.viagem;

import br.com.proposta.microservico.cartao.api.CartaoClientFeign;
import br.com.proposta.microservico.cartao.entidades.AvisoViagem;
import br.com.proposta.microservico.cartao.repositorys.AvisoViagemRepository;
import br.com.proposta.microservico.cartao.repositorys.CartaoRepository;
import br.com.proposta.microservico.cartao.requests.AvisoViagemRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class ViagemComponent {

    @Autowired
    private AvisoViagemRepository avisoViagemRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartaoClientFeign cartaoClientFeign;

    public void registrarNovaViagem(AvisoViagemRequest avisoViagemRequest, Long idCartao, HttpServletRequest request) throws Exception {
        AvisoViagem avisoViagem = avisoViagemRequest.convertToEntity(cartaoRepository, idCartao);
        avisoViagem.setIpCliente(request.getRemoteAddr());
        avisoViagemRepository.save(avisoViagem);
        SolicitacaoAvisoViagem solicitacaoAvisoViagem =
                new SolicitacaoAvisoViagem(avisoViagem.getDestinoViagem(),
                        avisoViagem.getDataTerminoViagem());
        ResultadoAvisoViagem resultadoAvisoViagem =
                cartaoClientFeign.avisoViagem(avisoViagem.getCartao().getIdCartao(),
                        solicitacaoAvisoViagem);
        if (resultadoAvisoViagem.getResultado().equals("FALHA")){
            throw new Exception("Erro ao comunicar à API de cartão");
        }
    }

}
