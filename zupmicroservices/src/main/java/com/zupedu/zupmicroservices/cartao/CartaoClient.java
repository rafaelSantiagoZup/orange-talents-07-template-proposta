package com.zupedu.zupmicroservices.cartao;

import com.zupedu.zupmicroservices.carteira.CarteiraRequest;
import com.zupedu.zupmicroservices.carteira.CarteiraResponse;
import com.zupedu.zupmicroservices.proposta.SolicitacaoAnaliseForm;
import com.zupedu.zupmicroservices.viagem.ViagemReqForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "cartao",url = "http://localhost:8888/api")
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes")
    Cartao addCartao(SolicitacaoAnaliseForm solicitacaoAnaliseForm);

    @RequestMapping(method = RequestMethod.GET, value = "/cartoes/{cartaoId}" )
    Cartao getCartao(@PathVariable("cartaoId") String cartaoId);

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{cartaoId}/bloqueios" )
    RespostaBloqueioForm bloqueiaCartao(@PathVariable("cartaoId") String cartaoId,ReqBloqueioForm bloqueioForm);

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{cartaoId}/avisos" )
    RespostaBloqueioForm avisaViagem(@PathVariable("cartaoId") String cartaoId, ViagemReqForm viagemReqForm);

    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{cartaoId}/carteiras" )
    CarteiraResponse associaCarteira(@PathVariable("cartaoId") String cartaoId, CarteiraRequest carteiraRequest);
}
