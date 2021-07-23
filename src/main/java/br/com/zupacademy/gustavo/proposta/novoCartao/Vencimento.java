package br.com.zupacademy.gustavo.proposta.novoCartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Vencimento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVencimento;
    private String id;
    private Integer dia;
    private LocalDateTime dataDeCriacao;

    public Vencimento(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vencimento that = (Vencimento) o;
        return id.equals(that.id) && dia.equals(that.dia) && dataDeCriacao.equals(that.dataDeCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dia, dataDeCriacao);
    }
}
