package br.com.zupacademy.gustavo.proposta.bloqueio;

import br.com.zupacademy.gustavo.proposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime bloqueadoEm = LocalDateTime.now();
    @NotBlank
    private String ipCliente;
    @NotBlank
    private String userAgent;
    @ManyToOne
    private Cartao cartao;
    @Enumerated(EnumType.STRING)
    private EstadoBloqueio bloqueio;

    public Bloqueio() {
    }

    public Bloqueio(String ipCliente, String userAgent, Cartao cartao, EstadoBloqueio bloqueio) {
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.cartao = cartao;
        this.bloqueio = bloqueio;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
