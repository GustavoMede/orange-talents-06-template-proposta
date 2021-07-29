package br.com.zupacademy.gustavo.proposta.proposta;

import br.com.zupacademy.gustavo.proposta.analiseProposta.ConsultaSolicitante;
import br.com.zupacademy.gustavo.proposta.analiseProposta.ConsultaSolicitanteRequest;
import br.com.zupacademy.gustavo.proposta.analiseProposta.ConsultaSolicitanteResponse;
import br.com.zupacademy.gustavo.proposta.endereco.Endereco;
import br.com.zupacademy.gustavo.proposta.endereco.EnderecoRepository;
import br.com.zupacademy.gustavo.proposta.cartao.CartaoRepository;
import br.com.zupacademy.gustavo.proposta.cartao.NovoCartao;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    ConsultaSolicitante consultaSolicitante;

    NovoCartao novoCartao;

    PropostaRepository propostaRepository;

    EnderecoRepository enderecoRepository;

    CartaoRepository cartaoRepository;

    public PropostaController(ConsultaSolicitante consultaSolicitante,
                              PropostaRepository propostaRepository, EnderecoRepository enderecoRepository,
                              NovoCartao novoCartao, CartaoRepository cartaoRepository) {
        this.consultaSolicitante = consultaSolicitante;
        this.propostaRepository = propostaRepository;
        this.enderecoRepository = enderecoRepository;
        this.novoCartao = novoCartao;
        this.cartaoRepository = cartaoRepository;
    }

    @PostMapping
    public ResponseEntity<URI> cadastra(@RequestBody @Valid PropostaRequest request) {
        Endereco enderecoRequest = request.getEndereco();
        Endereco enderecoExistente = enderecoRepository.findEndereco(enderecoRequest.getRua(),
                enderecoRequest.getNumero(), enderecoRequest.getCep());

        if(enderecoExistente == null){
            Proposta proposta = request.converte();
            propostaRepository.save(proposta);
            ConsultaSolicitanteRequest requestConsulta = new ConsultaSolicitanteRequest(request.getDocumento(),
                    request.getNome(), proposta.getId().toString());

            try{
                ConsultaSolicitanteResponse consultaSolicitanteResponse = consultaSolicitante.consultaSolicitante(requestConsulta);
                proposta.setEstado(EstadoProposta.ELEGIVEL);

                propostaRepository.save(proposta.encrypt());

                URI uri = UriComponentsBuilder.fromPath("/proposta/{id}").build().toUri();

                return ResponseEntity.created(uri).build();

            }catch(FeignException ex) {
                proposta.setEstado(EstadoProposta.NAO_ELEGIVEL);
                propostaRepository.save(proposta.encrypt());
                URI uri = UriComponentsBuilder.fromPath("/proposta/{id}").build().toUri();
                return ResponseEntity.created(uri).build();
            }
        }

        Proposta proposta = request.converteProdutoComEnderecoExistente(enderecoExistente);
        propostaRepository.save(proposta);
        ConsultaSolicitanteRequest requestConsulta = new ConsultaSolicitanteRequest(request.getDocumento(),
                request.getNome(), proposta.getId().toString());

        try{
            ConsultaSolicitanteResponse consultaSolicitanteResponse = consultaSolicitante.consultaSolicitante(requestConsulta);
            proposta.setEstado(EstadoProposta.ELEGIVEL);
            propostaRepository.save(proposta.encrypt());

            URI uri = UriComponentsBuilder.fromPath("/proposta/{id}").build().toUri();
            return ResponseEntity.created(uri).build();
        }catch(FeignException ex) {
            proposta.setEstado(EstadoProposta.NAO_ELEGIVEL);
            propostaRepository.save(proposta.encrypt());
            URI uri = UriComponentsBuilder.fromPath("/proposta/{id}").build().toUri();
            return ResponseEntity.created(uri).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoProposta> mostraEstadoProposta(@PathVariable Long id){
        Proposta proposta = propostaRepository.findId(id);
        if(!(proposta == null)){

            return ResponseEntity.ok(proposta.getEstado());
        }

        return ResponseEntity.notFound().build();
    }
}
