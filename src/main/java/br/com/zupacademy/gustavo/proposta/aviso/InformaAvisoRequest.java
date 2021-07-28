package br.com.zupacademy.gustavo.proposta.aviso;

import java.time.LocalDate;

public class InformaAvisoRequest {

    private String destino;
    private LocalDate validoAte;

    public InformaAvisoRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
