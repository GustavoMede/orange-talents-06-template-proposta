package br.com.zupacademy.gustavo.proposta.novoCartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "novo-cartao", url = "http://localhost:8888/api")
public interface NovoCartao {

    @PostMapping("/cartoes")
    NovoCartaoResponse solicitaNovoCartao(@RequestBody NovoCartaoRequest request);
}
