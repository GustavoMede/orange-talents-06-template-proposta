package br.com.zupacademy.gustavo.proposta.Cartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/bloqueio")
public class BloqueioController {

    @Autowired
    BloqueioRepository bloqueioRepository;

    @Autowired
    CartaoRepository cartaoRepository;

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> bloqueia(@PathVariable Long idCartao, HttpServletRequest httpServletRequest){

        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);

        if(cartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }else if(cartao.get().getBloqueio() == null){
            String userAgent = httpServletRequest.getHeader("User-Agent");
            String ipCliente = httpServletRequest.getRemoteAddr();
            Bloqueio bloqueio = new Bloqueio(ipCliente, userAgent, cartao.get());
            bloqueioRepository.save(bloqueio);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }
}
