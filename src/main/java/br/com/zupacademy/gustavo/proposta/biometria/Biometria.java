package br.com.zupacademy.gustavo.proposta.biometria;

import br.com.zupacademy.gustavo.proposta.Cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private byte[] fingerprint;
    @Column(updatable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();
    @ManyToOne
    private Cartao cartao;

    public Biometria() {
    }

    public Biometria(byte[] fingerprint, Cartao cartao) {
        this.fingerprint = fingerprint;
        this.cartao = cartao;
    }

    public byte[] getFingerprint() {
        return fingerprint;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public Biometria converte(Cartao cartao) {
        return new Biometria(fingerprint, cartao);
    }
}
