package br.com.zupacademy.gustavo.proposta.carteira;

import br.com.zupacademy.gustavo.proposta.bloqueio.Bloqueio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    @Query("select c from Carteira c " +
            "join c.cartao b " +
            "where b.idCartao = ?1 " +
            "and c.carteira = ?2")
    Optional<Carteira> findCartao(Long idCartao, TipoCarteira carteira);
}
