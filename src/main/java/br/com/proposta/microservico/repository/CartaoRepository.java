package br.com.proposta.microservico.repository;

import br.com.proposta.microservico.entidades.Cartao;
import br.com.proposta.microservico.entidades.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
