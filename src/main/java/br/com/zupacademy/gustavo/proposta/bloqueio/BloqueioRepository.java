package br.com.zupacademy.gustavo.proposta.bloqueio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BloqueioRepository extends JpaRepository<Bloqueio, Long> {

    @Query("select b from Bloqueio b " +
            "join b.cartao c " +
            "where c.idCartao = ?1")
    Optional<Bloqueio> findCartao(Long idCartao);
}
