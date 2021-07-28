package br.com.zupacademy.gustavo.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "novo-cartao", url = "${cartoes.host}")
public interface NovoCartao {

    @PostMapping("/cartoes")
    NovoCartaoResponse solicitaNovoCartao(@RequestBody NovoCartaoRequest request);
}
