package br.com.zupacademy.gustavo.proposta.novoCartao;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idParcela;
    private String id;
    private Integer quantidade;
    private Integer valor;
    @ManyToOne
    private NovoCartaoResponse novoCartaoResponse;

    public Parcela(String id, Integer quantidade, Integer valor, NovoCartaoResponse novoCartaoResponse) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.novoCartaoResponse = novoCartaoResponse;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcela parcela = (Parcela) o;
        return id.equals(parcela.id) && quantidade.equals(parcela.quantidade) && valor.equals(parcela.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantidade, valor);
    }

    public Parcela converte(NovoCartaoResponse novoCartaoResponse) {
        return new Parcela(id, quantidade, valor, novoCartaoResponse);
    }
}
