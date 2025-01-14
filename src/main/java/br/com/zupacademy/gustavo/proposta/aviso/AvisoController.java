package br.com.zupacademy.gustavo.proposta.aviso;

import br.com.zupacademy.gustavo.proposta.cartao.Cartao;
import br.com.zupacademy.gustavo.proposta.cartao.CartaoRepository;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/aviso")
public class AvisoController {

    CartaoRepository cartaoRepository;

    AvisoRepository avisoRepository;

    InformaAviso informaAviso;

    public AvisoController(CartaoRepository cartaoRepository, AvisoRepository avisoRepository, InformaAviso informaAviso) {
        this.cartaoRepository = cartaoRepository;
        this.avisoRepository = avisoRepository;
        this.informaAviso = informaAviso;
    }

    @PostMapping("/{idCartao}")
    private ResponseEntity<?> cadastra(@PathVariable Long idCartao, @RequestBody AvisoRequest request,
                                       HttpServletRequest httpServletRequest){
        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);

        if(cartao.isPresent()){
            try {
                String userAgent = httpServletRequest.getHeader("User-Agent");
                String ipCliente = httpServletRequest.getRemoteAddr();

                InformaAvisoRequest informaAvisoRequest = new InformaAvisoRequest(request.getDestino(), request.getDataTermino());
                informaAviso.informaAviso(cartao.get().getId(), informaAvisoRequest);
                Aviso aviso = new Aviso(request.getDestino(), request.getDataTermino(), ipCliente, userAgent, cartao.get());
                avisoRepository.save(aviso);

                return ResponseEntity.ok().build();
            }catch(FeignException ex){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
