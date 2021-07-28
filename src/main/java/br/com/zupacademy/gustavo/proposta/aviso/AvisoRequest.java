package br.com.zupacademy.gustavo.proposta.aviso;

import br.com.zupacademy.gustavo.proposta.Cartao.Cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoRequest {

    @NotBlank
    private String destino;
    @NotNull
    @Future
    private LocalDate dataTermino;

    public AvisoRequest(String destino, LocalDate dataTermino) {
        this.destino = destino;
        this.dataTermino = dataTermino;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }
}
