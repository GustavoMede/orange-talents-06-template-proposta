package br.com.zupacademy.gustavo.proposta.novoCartao;

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
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private Set<Bloqueio> bloqueios = new HashSet<>();
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private Set<Carteira> carteiras = new HashSet<>();
    private Integer limite;
    private String idProposta;

    public Cartao() {
    }

    public Cartao(String idCartao, LocalDateTime emitidoEm, String titular, Set<Bloqueio> bloqueios,
                  Set<Carteira> carteiras, Integer limite,
                  String idProposta) {
        this.id = idCartao;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        Set<Bloqueio> bloqueioSet = bloqueios.stream()
                .map(bloqueio -> bloqueio.converte(this)).collect(Collectors.toSet());
        this.bloqueios.addAll(bloqueioSet);
        this.bloqueios = bloqueios;
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

    public Set<Bloqueio> getBloqueios() {
        return bloqueios;
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

    public Cartao hasheiaNumero() {
        this.id = String.valueOf(id.hashCode());
        return this;
    }
}
