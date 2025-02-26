package br.com.zupacademy.gustavo.proposta.cartao;

import br.com.zupacademy.gustavo.proposta.bloqueio.Bloqueio;
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

    @Scheduled(fixedDelay = 10000)
    public void geraNovoCartao(){
        List<Proposta> listaDePropostas = propostaRepository.findAll();
        for (Proposta listaDeProposta : listaDePropostas) {
            Proposta proposta = propostaRepository.findId(listaDeProposta.getId());
            if (!(proposta == null) && proposta.getEstado() == EstadoProposta.ELEGIVEL &&
                    proposta.getNumeroCartao() == null) {

                try {
                    String idProposta = proposta.getId().toString();
                    NovoCartaoResponse novoCartaoResponse = novoCartao.solicitaNovoCartao(idProposta);
                    proposta.setNumeroCartao(novoCartaoResponse.getId());
                    cartaoRepository.save(new Cartao(novoCartaoResponse.getId(), novoCartaoResponse.getEmitidoEm(),
                            novoCartaoResponse.getTitular(), novoCartaoResponse.getLimite(),
                            novoCartaoResponse.getIdProposta()));
                    propostaRepository.save(proposta);
                }catch(FeignException ex){
                    System.out.println(ex);
                }
            }
        }
    }
}