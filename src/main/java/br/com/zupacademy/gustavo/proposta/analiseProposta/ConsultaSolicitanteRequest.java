package br.com.zupacademy.gustavo.proposta.analiseProposta;

import br.com.zupacademy.gustavo.proposta.proposta.Proposta;
import br.com.zupacademy.gustavo.proposta.proposta.PropostaRepository;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class ConsultaSolicitanteRequest {

    @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @NotBlank
    private String idProposta;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ConsultaSolicitanteRequest(String documento, String nome, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
