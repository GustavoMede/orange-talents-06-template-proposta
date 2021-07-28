package br.com.zupacademy.gustavo.proposta.cartao;

import br.com.zupacademy.gustavo.proposta.bloqueio.Bloqueio;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCartao;
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private Integer limite;
    private String idProposta;

    public Cartao() {
    }

    public Cartao(String id, LocalDateTime emitidoEm, String titular, Integer limite,
                  String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
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

    public Integer getLimite() {
        return limite;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
