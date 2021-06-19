package br.com.proposta.microservico.proposta.repositorys;

import br.com.proposta.microservico.proposta.entidades.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Proposta findByCartaoId(Long idCartao);
}
