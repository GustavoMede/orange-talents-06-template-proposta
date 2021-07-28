package br.com.zupacademy.gustavo.proposta.carteira;

import br.com.zupacademy.gustavo.proposta.cartao.Cartao;
import br.com.zupacademy.gustavo.proposta.cartao.CartaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/carteira")
public class CarteiraController {

    CarteiraRepository carteiraRepository;

    CartaoRepository cartaoRepository;

    SolicitaCarteira solicitaCarteira;

    public CarteiraController(CarteiraRepository carteiraRepository, CartaoRepository cartaoRepository,
                              SolicitaCarteira solicitaCarteira) {
        this.carteiraRepository = carteiraRepository;
        this.cartaoRepository = cartaoRepository;
        this.solicitaCarteira = solicitaCarteira;
    }

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> cadastra(@PathVariable Long idCartao, @RequestBody CarteiraRequest request){
        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
        Optional<Carteira> carteiraBuscada = carteiraRepository.findCartao(idCartao, request.getCarteira());

        if(cartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }else if(carteiraBuscada.isEmpty()){
            NovaCarteiraRequest novaCarteiraRequest = new NovaCarteiraRequest(request.getEmail(), request.getCarteira().toString());
            solicitaCarteira.solicitaCarteira(cartao.get().getId(), novaCarteiraRequest);
            Carteira carteira = new Carteira(request.getEmail(), request.getCarteira(), cartao.get());
            carteiraRepository.save(carteira);

            URI uri = UriComponentsBuilder.fromPath("/carteira/{id}").build().toUri();

            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.unprocessableEntity().build();

    }
}
