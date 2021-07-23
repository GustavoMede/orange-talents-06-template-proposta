package br.com.zupacademy.gustavo.proposta.novoCartao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Cartao")
public class NovoCartaoResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCartao;
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    @OneToMany(mappedBy = "novoCartaoResponse", cascade = CascadeType.PERSIST)
    private Set<Bloqueio> bloqueios = new HashSet<>();
    @OneToMany(mappedBy = "novoCartaoResponse", cascade = CascadeType.PERSIST)
    private Set<Aviso> avisos = new HashSet<>();
    @OneToMany(mappedBy = "novoCartaoResponse", cascade = CascadeType.PERSIST)
    private Set<Carteira> carteiras = new HashSet<>();
    @OneToMany(mappedBy = "novoCartaoResponse", cascade = CascadeType.PERSIST)
    private Set<Parcela> parcelas = new HashSet<>();
    private Integer limite;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private Renegociacao renegociacao;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private Vencimento vencimento;
    private String idProposta;

    public NovoCartaoResponse(String idCartao, LocalDateTime emitidoEm, String titular, Set<Bloqueio> bloqueios,
                              Set<Aviso> avisos, Set<Carteira> carteiras, Set<Parcela> parcelas, Integer limite,
                              Renegociacao renegociacao, Vencimento vencimento, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        Set<Bloqueio> bloqueioSet = bloqueios.stream()
                .map(bloqueio -> bloqueio.converte(this)).collect(Collectors.toSet());
        this.bloqueios.addAll(bloqueioSet);
        this.bloqueios = bloqueios;
        Set<Aviso> avisoSet = avisos.stream()
                .map(aviso -> aviso.converte(this)).collect(Collectors.toSet());
        this.avisos.addAll(avisoSet);
        Set<Carteira> carteiraSet = carteiras.stream()
                .map(carteira -> carteira.converte(this)).collect(Collectors.toSet());
        this.carteiras.addAll(carteiraSet);
        Set<Parcela> parcelaSet = parcelas.stream()
                .map(parcela -> parcela.converte(this)).collect(Collectors.toSet());
        this.parcelas.addAll(parcelaSet);
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
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

    public Set<Aviso> getAvisos() {
        return avisos;
    }

    public Set<Carteira> getCarteiras() {
        return carteiras;
    }

    public Set<Parcela> getParcelas() {
        return parcelas;
    }

    public Integer getLimite() {
        return limite;
    }

    public Renegociacao getRenegociacao() {
        return renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public NovoCartaoResponse hasheiaNumero() {
        this.id = String.valueOf(id.hashCode());
        return this;
    }
}
