package br.com.zupacademy.gustavo.proposta.bloqueio;

import br.com.zupacademy.gustavo.proposta.cartao.NovoCartaoResponse;

import java.time.LocalDateTime;
import java.util.Objects;

public class BloqueioResponse {

    private String id;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;
    private NovoCartaoResponse cartaoResponse;

    public BloqueioResponse(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo,
                            NovoCartaoResponse cartaoResponse) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
        this.cartaoResponse = cartaoResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BloqueioResponse that = (BloqueioResponse) o;
        return ativo == that.ativo && id.equals(that.id) && sistemaResponsavel.equals(that.sistemaResponsavel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sistemaResponsavel, ativo);
    }

    public BloqueioResponse converte(NovoCartaoResponse cartaoResponse) {
        return new BloqueioResponse(id, bloqueadoEm, sistemaResponsavel, ativo, cartaoResponse);
    }
}
