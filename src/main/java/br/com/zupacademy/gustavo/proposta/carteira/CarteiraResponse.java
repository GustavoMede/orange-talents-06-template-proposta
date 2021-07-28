package br.com.zupacademy.gustavo.proposta.carteira;

import br.com.zupacademy.gustavo.proposta.cartao.NovoCartaoResponse;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

public class CarteiraResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCarteira;
    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;
    @ManyToOne
    private NovoCartaoResponse cartao;

    public CarteiraResponse(String id, String email, LocalDateTime associadaEm, String emissor, NovoCartaoResponse cartao) {
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
        CarteiraResponse that = (CarteiraResponse) o;
        return id.equals(that.id) && email.equals(that.email) && associadaEm.equals(that.associadaEm) && emissor.equals(that.emissor) && cartao.equals(that.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, associadaEm, emissor, cartao);
    }

    public CarteiraResponse converte(NovoCartaoResponse cartaoResponse) {
        return new CarteiraResponse(id, email, associadaEm, emissor, cartaoResponse);
    }
}
