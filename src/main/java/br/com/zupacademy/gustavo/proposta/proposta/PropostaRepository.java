package br.com.zupacademy.gustavo.proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    @Query("select p from Proposta p where p.id = ?1")
    Proposta findId(Long id);
}
