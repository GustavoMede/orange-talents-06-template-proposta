package br.com.zupacademy.gustavo.proposta.novoCartao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBloqueio;
    private String id;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;
    @ManyToOne
    private Cartao cartao;

    public Bloqueio(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo,
                    Cartao cartao) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
        this.cartao = cartao;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bloqueio bloqueio = (Bloqueio) o;
        return ativo == bloqueio.ativo && id.equals(bloqueio.id) && bloqueadoEm.equals(bloqueio.bloqueadoEm) && sistemaResponsavel.equals(bloqueio.sistemaResponsavel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bloqueadoEm, sistemaResponsavel, ativo);
    }

    public Bloqueio converte(Cartao cartao) {
        return new Bloqueio(id, bloqueadoEm, sistemaResponsavel, ativo, cartao);
    }
}
