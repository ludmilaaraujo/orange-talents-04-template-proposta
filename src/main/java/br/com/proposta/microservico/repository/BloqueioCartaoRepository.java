package br.com.proposta.microservico.repository;

import br.com.proposta.microservico.entidades.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloqueioCartaoRepository extends JpaRepository<Cartao, Long> {

    Optional<Cartao> findById(Long id);
}
