package br.com.zupacademy.gustavo.proposta.carteira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "nova-carteira", url = "${cartoes.host}")
public interface SolicitaCarteira {

    @PostMapping("/cartoes/{id}/carteiras")
    void solicitaCarteira(@PathVariable String id, @RequestBody NovaCarteiraRequest request);
}
