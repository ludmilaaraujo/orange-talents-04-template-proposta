package br.com.proposta.microservico.cartao.repositorys;

import br.com.proposta.microservico.cartao.entidades.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloqueioCartaoRepository extends JpaRepository<Cartao, Long> {

    Optional<Cartao> findById(Long id);
}
