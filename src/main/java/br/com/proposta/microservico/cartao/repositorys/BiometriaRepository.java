package br.com.proposta.microservico.cartao.repositorys;

import br.com.proposta.microservico.cartao.entidades.Biometria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiometriaRepository extends JpaRepository<Biometria, Long> {
}
