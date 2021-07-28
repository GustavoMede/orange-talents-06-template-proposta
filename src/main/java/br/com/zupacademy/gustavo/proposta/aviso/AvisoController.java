package br.com.zupacademy.gustavo.proposta.aviso;

import br.com.zupacademy.gustavo.proposta.Cartao.Cartao;
import br.com.zupacademy.gustavo.proposta.Cartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/aviso")
public class AvisoController {

    CartaoRepository cartaoRepository;

    AvisoRepository avisoRepository;

    public AvisoController(CartaoRepository cartaoRepository, AvisoRepository avisoRepository) {
        this.cartaoRepository = cartaoRepository;
        this.avisoRepository = avisoRepository;
    }

    @PostMapping("/{idCartao}")
    private ResponseEntity<?> cadastra(@PathVariable Long idCartao, @RequestBody AvisoRequest request,
                                       HttpServletRequest httpServletRequest){
        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);

        if(cartao.isPresent()){
            String userAgent = httpServletRequest.getHeader("User-Agent");
            String ipCliente = httpServletRequest.getRemoteAddr();

            Aviso aviso = request.converte(ipCliente, userAgent, cartao.get());

            avisoRepository.save(aviso);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
