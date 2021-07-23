package br.com.zupacademy.gustavo.proposta.novoCartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Renegociacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRenegociacao;
    private String id;
    private Integer quantidade;
    private Integer valor;
    private LocalDateTime dataDeCriacao;

    public Renegociacao(String id, Integer quantidade, Integer valor, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Integer getValor() {
        return valor;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Renegociacao that = (Renegociacao) o;
        return id.equals(that.id) && quantidade.equals(that.quantidade) && valor.equals(that.valor) && dataDeCriacao.equals(that.dataDeCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantidade, valor, dataDeCriacao);
    }
}
