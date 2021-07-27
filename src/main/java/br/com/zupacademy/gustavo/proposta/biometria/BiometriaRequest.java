package br.com.zupacademy.gustavo.proposta.biometria;

import br.com.zupacademy.gustavo.proposta.annotation.IsBase64;
import br.com.zupacademy.gustavo.proposta.Cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class BiometriaRequest {

    @NotBlank
    @IsBase64
    private byte[] fingerprint;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BiometriaRequest(byte[] fingerprint) {
        this.fingerprint = fingerprint;
    }

    public byte[] getFingerprint() {
        return fingerprint;
    }

    public Biometria converte(Cartao cartao) {
        byte[] encodedFingerprint = Base64.getEncoder().encode(fingerprint);
        return new Biometria(encodedFingerprint, cartao);
    }
}
