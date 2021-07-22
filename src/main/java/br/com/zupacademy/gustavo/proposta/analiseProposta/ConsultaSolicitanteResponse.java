package br.com.zupacademy.gustavo.proposta.analiseProposta;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ConsultaSolicitanteResponse {

    private String documento, nome, idProposta;
    @Enumerated(EnumType.STRING)
    private ResultadoSolicitacao resultadoSolicitacao;

    public ConsultaSolicitanteResponse(String documento, String nome, String idProposta, ResultadoSolicitacao resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
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

    public ResultadoSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
