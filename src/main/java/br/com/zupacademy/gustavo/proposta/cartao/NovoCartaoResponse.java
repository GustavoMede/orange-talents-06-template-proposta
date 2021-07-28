package br.com.zupacademy.gustavo.proposta.cartao;

import br.com.zupacademy.gustavo.proposta.aviso.AvisoResponse;
import br.com.zupacademy.gustavo.proposta.bloqueio.BloqueioResponse;
import br.com.zupacademy.gustavo.proposta.carteira.CarteiraResponse;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class NovoCartaoResponse {

    @NotBlank
    private String id;
    @NotNull
    private LocalDateTime emitidoEm;
    @NotBlank
    private String titular;
    @OneToMany
    private Set<BloqueioResponse> bloqueios = new HashSet<>();
    @OneToMany
    private Set<AvisoResponse> avisos = new HashSet<>();
    @OneToMany
    private Set<CarteiraResponse> carteiras = new HashSet<>();
    @Positive
    private Integer limite;
    @NotBlank
    private String idProposta;

    public NovoCartaoResponse(String id, LocalDateTime emitidoEm, String titular, Set<BloqueioResponse> bloqueios,
                              Set<AvisoResponse> avisos, Set<CarteiraResponse> carteiras, Integer limite, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        Set<BloqueioResponse> bloqueioResponseSet = bloqueios.stream()
                .map(bloqueio -> bloqueio.converte(this)).collect(Collectors.toSet());
        this.bloqueios.addAll(bloqueioResponseSet);
        Set<AvisoResponse> avisoResponseSet = avisos.stream()
                .map(aviso -> aviso.converte(this)).collect(Collectors.toSet());
        this.avisos.addAll(avisoResponseSet);
        this.avisos = avisos;
        Set<CarteiraResponse> carteiraResponseSet = carteiras.stream()
                .map(carteira -> carteira.converte(this)).collect(Collectors.toSet());
        this.carteiras.addAll(carteiraResponseSet);
        this.limite = limite;
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

    public Set<BloqueioResponse> getBloqueio() {
        return bloqueios;
    }

    public Set<AvisoResponse> getAvisos() {
        return avisos;
    }

    public Set<CarteiraResponse> getCarteiras() {
        return carteiras;
    }

    public Integer getLimite() {
        return limite;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
