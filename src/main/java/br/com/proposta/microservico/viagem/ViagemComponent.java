package br.com.proposta.microservico.viagem;

import br.com.proposta.microservico.entidades.AvisoViagem;
import br.com.proposta.microservico.repository.AvisoViagemRepository;
import br.com.proposta.microservico.repository.CartaoRepository;
import br.com.proposta.microservico.request.AvisoViagemRequest;
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

    public void registrarNovaViagem(AvisoViagemRequest avisoViagemRequest, Long idCartao, HttpServletRequest request) {
        AvisoViagem avisoViagem = avisoViagemRequest.convertToEntity(cartaoRepository, idCartao);
        avisoViagem.setIpCliente(request.getRemoteAddr());
        avisoViagemRepository.save(avisoViagem);
        avisarSistemaLegadoDaViagem(avisoViagem);
    }

    private void avisarSistemaLegadoDaViagem(AvisoViagem avisoViagem) {
        log.info(avisoViagem.respostaAvisoViagem());
    }
}
