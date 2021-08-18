package com.zupedu.zupmicroservices.proposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@FeignClient(name ="status",url = "localhost:9999")
@Component
public interface StatusClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao")
    StatusServiceResponseDTO addSolicitacao(SolicitacaoAnaliseForm form);
}
