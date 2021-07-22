package br.com.zupacademy.gustavo.proposta.endereco;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String rua;
    @NotNull
    private Integer numero;
    @NotBlank @Size(max = 8)
    private String cep;

    public Endereco(String rua, Integer numero, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
    }

    public Endereco(){
    }

    public String getRua() {
        return rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }
}
