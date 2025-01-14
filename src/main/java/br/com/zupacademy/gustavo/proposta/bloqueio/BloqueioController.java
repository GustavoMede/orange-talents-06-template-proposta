package br.com.zupacademy.gustavo.proposta.bloqueio;

import br.com.zupacademy.gustavo.proposta.cartao.Cartao;
import br.com.zupacademy.gustavo.proposta.cartao.CartaoRepository;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/bloqueio")
public class BloqueioController {

    BloqueioRepository bloqueioRepository;

    CartaoRepository cartaoRepository;

    BloqueiaCartao bloqueiaCartao;

    public BloqueioController(BloqueioRepository bloqueioRepository, CartaoRepository cartaoRepository,
                              BloqueiaCartao bloqueiaCartao) {
        this.bloqueioRepository = bloqueioRepository;
        this.cartaoRepository = cartaoRepository;
        this.bloqueiaCartao = bloqueiaCartao;
    }

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> bloqueia(@PathVariable Long idCartao, HttpServletRequest httpServletRequest){

        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
        Optional<Bloqueio> bloqueioBuscado = bloqueioRepository.findCartao(idCartao);

        if(cartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }else if(bloqueioBuscado.isEmpty()){

            try {
                String userAgent = httpServletRequest.getHeader("User-Agent");
                String ipCliente = httpServletRequest.getRemoteAddr();

                BloqueioRequest bloqueioRequest = new BloqueioRequest("proposta-API");
                bloqueiaCartao.solicitaBloqueio(cartao.get().getId(), bloqueioRequest);
                Bloqueio bloqueio = new Bloqueio(ipCliente, userAgent, cartao.get(), EstadoBloqueio.BLOQUEADO);

                bloqueioRepository.save(bloqueio);

                return ResponseEntity.ok().build();
            }catch(FeignException ex){
                System.out.println(ex);
            }
        }
        return ResponseEntity.unprocessableEntity().build();
    }
}
