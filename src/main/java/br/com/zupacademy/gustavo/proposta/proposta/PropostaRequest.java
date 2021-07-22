package br.com.zupacademy.gustavo.proposta.proposta;

import br.com.zupacademy.gustavo.proposta.annotation.CpfOuCnpj;
import br.com.zupacademy.gustavo.proposta.endereco.Endereco;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotNull
    private Endereco endereco;
    @CpfOuCnpj
    private String documento;
    @NotNull @Positive
    private BigDecimal salario;

    public PropostaRequest(String email, String nome, Endereco endereco, String documento, BigDecimal salario) {
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.documento = documento;
        this.salario = salario;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Proposta converte() {
        Integer salarioConvertido = salario.multiply(BigDecimal.valueOf(100)).intValue();
        Proposta proposta = new Proposta(this.email, this.nome, this.endereco,
                this.documento, salarioConvertido);
        return proposta;
    }

    public Proposta converteProdutoComEnderecoExistente(Endereco endereco){
        Integer salarioConvertido = salario.multiply(BigDecimal.valueOf(100)).intValue();
        Proposta proposta = new Proposta(this.email, this.nome, endereco,
                this.documento, salarioConvertido);
        return proposta;
    }
}
