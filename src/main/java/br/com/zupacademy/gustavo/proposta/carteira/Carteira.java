package br.com.zupacademy.gustavo.proposta.carteira;

import br.com.zupacademy.gustavo.proposta.cartao.Cartao;

import javax.persistence.*;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCarteira;
    private String email;
    @Enumerated(EnumType.STRING)
    private TipoCarteira carteira;
    @ManyToOne
    private Cartao cartao;

    public Carteira() {
    }

    public Carteira(String email, TipoCarteira carteira, Cartao cartao) {
        this.email = email;
        this.carteira = carteira;
        this.cartao = cartao;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }
}
