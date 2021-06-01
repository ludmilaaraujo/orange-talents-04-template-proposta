package br.com.proposta.microservico.repository;

import br.com.proposta.microservico.entidades.Biometria;
import br.com.proposta.microservico.entidades.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiometriaRepository extends JpaRepository<Biometria, Long> {
}
