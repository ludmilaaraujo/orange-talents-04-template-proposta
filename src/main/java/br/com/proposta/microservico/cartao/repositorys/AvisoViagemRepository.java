package br.com.proposta.microservico.cartao.repositorys;

import br.com.proposta.microservico.cartao.entidades.AvisoViagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisoViagemRepository extends JpaRepository<AvisoViagem, Long> {
}
