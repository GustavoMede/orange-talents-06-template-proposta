package br.com.zupacademy.gustavo.proposta.novoCartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class NovoCartaoRequest {

    @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @NotBlank
    private String idProposta;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovoCartaoRequest(String documento, String nome, String idProposta) {
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
