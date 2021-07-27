package br.com.zupacademy.gustavo.proposta.biometria;

import br.com.zupacademy.gustavo.proposta.Cartao.CartaoRepository;
import br.com.zupacademy.gustavo.proposta.Cartao.Cartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/biometria")
public class BiometriaController {

    @Autowired
    BiometriaRepository biometriaRepository;

    @Autowired
    CartaoRepository cartaoRepository;

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> cadastra(@PathVariable Long idCartao, @RequestBody BiometriaRequest request){
        Optional<Cartao> cartaoBuscado = cartaoRepository.findById(idCartao);
        if(cartaoBuscado.isPresent()){
            Cartao cartao = cartaoBuscado.get();
            Biometria biometria = request.converte(cartao);

            biometriaRepository.save(biometria);

            URI uri = UriComponentsBuilder.fromPath("/biometria/{id}").build().toUri();

            return ResponseEntity.created(uri).build();
        }
            return ResponseEntity.notFound().build();
    }
}
