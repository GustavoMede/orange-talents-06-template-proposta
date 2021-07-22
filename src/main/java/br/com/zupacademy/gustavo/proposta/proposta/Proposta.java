package br.com.zupacademy.gustavo.proposta.proposta;

import br.com.zupacademy.gustavo.proposta.endereco.Endereco;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String nome;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Endereco endereco;
    private String documento;
    private Integer salario;

    public Proposta(@NotBlank @Email String email, @NotBlank String nome, @NotNull Endereco endereco,
                    @NotBlank String documento, @NotNull @NotEmpty @Positive Integer salario) {
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.documento = documento;
        this.salario = salario;
    }
}
