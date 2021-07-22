package br.com.zupacademy.gustavo.proposta.analiseProposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "consulta-solicitante", url = "http://localhost:9999/api")
public interface ConsultaSolicitante {

    @PostMapping("/solicitacao")
    ConsultaSolicitanteResponse consultaSolicitante(@RequestBody ConsultaSolicitanteRequest request);
}
