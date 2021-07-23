package br.com.zupacademy.gustavo.proposta.novoCartao;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate validoAte;
    private String destino;
    @ManyToOne
    private NovoCartaoResponse novoCartaoResponse;

    public Aviso(LocalDate validoAte, String destino, NovoCartaoResponse novoCartaoResponse) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.novoCartaoResponse = novoCartaoResponse;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aviso aviso = (Aviso) o;
        return validoAte.equals(aviso.validoAte) && destino.equals(aviso.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(validoAte, destino);
    }

    public Aviso converte(NovoCartaoResponse novoCartaoResponse) {
        return new Aviso(validoAte, destino, novoCartaoResponse);
    }
}
