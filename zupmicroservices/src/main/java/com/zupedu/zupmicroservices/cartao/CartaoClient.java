package com.zupedu.zupmicroservices.cartao;

import com.zupedu.zupmicroservices.proposta.SolicitacaoAnaliseForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.Future;

@FeignClient(name = "cartao",url = "http://localhost:8888/api")
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes")
    Cartao addCartao(SolicitacaoAnaliseForm solicitacaoAnaliseForm);
}
