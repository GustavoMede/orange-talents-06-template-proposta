package br.com.zupacademy.gustavo.proposta.bloqueio;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bloqueia-cartao", url = "${cartoes.host}")
public interface BloqueiaCartao {

    @PostMapping("/cartoes/{id}/bloqueios")
    void solicitaBloqueio(@PathVariable String id, @RequestBody BloqueioRequest request);
}
