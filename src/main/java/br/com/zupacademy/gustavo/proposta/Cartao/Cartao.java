package br.com.zupacademy.gustavo.proposta.Cartao;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCartao;
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    @OneToOne(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private Bloqueio bloqueio;
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private Set<Carteira> carteiras = new HashSet<>();
    private Integer limite;
    private String idProposta;

    public Cartao() {
    }

    public Cartao(String id, LocalDateTime emitidoEm, String titular, Bloqueio bloqueio,
                  Set<Carteira> carteiras, Integer limite,
                  String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueio = bloqueio;
        Set<Carteira> carteiraSet = carteiras.stream()
                .map(carteira -> carteira.converte(this)).collect(Collectors.toSet());
        this.carteiras.addAll(carteiraSet);
        this.limite = limite;
        this.idProposta = idProposta;
    }

    public Long getIdCartao() {
        return idCartao;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public Bloqueio getBloqueio() {
        return bloqueio;
    }


    public Set<Carteira> getCarteiras() {
        return carteiras;
    }


    public Integer getLimite() {
        return limite;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
