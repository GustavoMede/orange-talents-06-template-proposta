package br.com.zupacademy.gustavo.proposta.novoCartao;

import br.com.zupacademy.gustavo.proposta.proposta.EstadoProposta;
import br.com.zupacademy.gustavo.proposta.proposta.Proposta;
import br.com.zupacademy.gustavo.proposta.proposta.PropostaRepository;
import feign.FeignException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class GeraNovoCartao {

    PropostaRepository propostaRepository;

    NovoCartao novoCartao;

    CartaoRepository cartaoRepository;

    public GeraNovoCartao(PropostaRepository propostaRepository, NovoCartao novoCartao, CartaoRepository cartaoRepository) {
        this.propostaRepository = propostaRepository;
        this.novoCartao = novoCartao;
        this.cartaoRepository = cartaoRepository;
    }

    @Scheduled(fixedDelay = 1000)
    public void geraNovoCartao(){
        List<Proposta> listaDePropostas = propostaRepository.findAll();
        for (Proposta listaDeProposta : listaDePropostas) {
            Proposta proposta = propostaRepository.findId(listaDeProposta.getId());
            if (!(proposta == null) && proposta.getEstado() == EstadoProposta.ELEGIVEL &&
                    proposta.getNumeroCartao() == null) {
                NovoCartaoRequest novoCartaoRequest = new NovoCartaoRequest(proposta.getDocumento(),
                        proposta.getNome(), proposta.getId().toString());

                try {
                    NovoCartaoResponse novoCartaoResponse = novoCartao.solicitaNovoCartao(novoCartaoRequest);
                    proposta.setNumeroCartao(novoCartaoResponse.getId());
                    NovoCartaoResponse cartao = novoCartaoResponse.hasheiaNumero();
                    cartaoRepository.save(cartao);
                    propostaRepository.save(proposta);
                } catch (FeignException ignored) {
                }
            }
        }
    }
}