package br.com.zupacademy.gustavo.proposta.aviso;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "informa-aviso", url = "${cartoes.host}")
public interface InformaAviso {

    @PostMapping("/cartoes/{id}/avisos")
    public void informaAviso(@PathVariable String id, @RequestBody InformaAvisoRequest request);
}
