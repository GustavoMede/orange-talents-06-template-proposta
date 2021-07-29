package br.com.zupacademy.gustavo.proposta.proposta;

import br.com.zupacademy.gustavo.proposta.endereco.Endereco;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    @Enumerated(EnumType.STRING)
    private EstadoProposta estado;
    private String numeroCartao;

    public Proposta() {
    }

    public Proposta(@NotBlank @Email String email, @NotBlank String nome, @NotNull Endereco endereco,
                    @NotBlank String documento, @NotNull @NotEmpty @Positive Integer salario) {
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.documento = documento;
        this.salario = salario;
    }

    public Long getId() {
        return id;
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

    public String getDocumento() {
        return documento;
    }

    public Integer getSalario() {
        return salario;
    }

    public EstadoProposta getEstado() {
        return estado;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public void setEstado(EstadoProposta estado) {
        this.estado = estado;
    }

    public Proposta encrypt() {
        this.documento = new BCryptPasswordEncoder().encode(documento);
        return this;
    }
}
