package br.com.zupacademy.gustavo.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "novo-cartao", url = "${cartoes.host}")
public interface NovoCartao {

    @GetMapping("/cartoes")
    NovoCartaoResponse solicitaNovoCartao(@RequestParam String idProposta);
}
