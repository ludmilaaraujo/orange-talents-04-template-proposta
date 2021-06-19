package br.com.proposta.microservico.cartao.repositorys;

import br.com.proposta.microservico.cartao.entidades.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
