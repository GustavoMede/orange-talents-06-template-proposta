package br.com.zupacademy.gustavo.proposta.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("select e from Endereco e where e.rua = ?1 and e.numero = ?2 and e.cep = ?3")
    Endereco findEndereco(String rua, Integer numero, String cep);
}
