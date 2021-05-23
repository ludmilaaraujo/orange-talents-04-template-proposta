package br.com.proposta.microservico.repository;

import br.com.proposta.microservico.entidades.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
