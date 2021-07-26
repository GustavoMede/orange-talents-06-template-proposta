package br.com.zupacademy.gustavo.proposta.novoCartao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCarteira;
    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;
    @ManyToOne
    private Cartao cartao;

    public Carteira(String id, String email, LocalDateTime associadaEm, String emissor, Cartao cartao) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
        this.cartao = cartao;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carteira carteira = (Carteira) o;
        return id.equals(carteira.id) && email.equals(carteira.email) && associadaEm.equals(carteira.associadaEm) && emissor.equals(carteira.emissor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, associadaEm, emissor);
    }

    public Carteira converte(Cartao cartao) {
        return new Carteira(id, email, associadaEm, emissor, cartao);
    }
}
