package br.com.zupacademy.gustavo.proposta.aviso;

import br.com.zupacademy.gustavo.proposta.Cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String destino;
    private LocalDate dataTermino;
    private LocalDateTime dataAviso = LocalDateTime.now();
    private String ipCliente;
    private String userAgent;
    @ManyToOne
    private Cartao cartao;

    public Aviso() {
    }

    public Aviso(String destino, LocalDate dataTermino,
                 String ipCliente, String userAgent, Cartao cartao) {
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public LocalDateTime getDataAviso() {
        return dataAviso;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
