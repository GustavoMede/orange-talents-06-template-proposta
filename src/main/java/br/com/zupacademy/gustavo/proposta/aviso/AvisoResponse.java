package br.com.zupacademy.gustavo.proposta.aviso;

import br.com.zupacademy.gustavo.proposta.cartao.NovoCartaoResponse;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoResponse {

    @NotBlank
    private String destino;
    @NotNull
    private LocalDate dataTermino;
    private NovoCartaoResponse cartaoResponse;

    public AvisoResponse(String destino, LocalDate dataTermino, NovoCartaoResponse cartaoResponse) {
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.cartaoResponse = cartaoResponse;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public AvisoResponse converte(NovoCartaoResponse cartaoResponse){
        return new AvisoResponse(destino, dataTermino, cartaoResponse);
    }
}
