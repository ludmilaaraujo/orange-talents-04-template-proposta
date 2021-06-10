package br.com.proposta.microservico.repository;

import br.com.proposta.microservico.entidades.AvisoViagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisoViagemRepository extends JpaRepository<AvisoViagem, Long> {
}
