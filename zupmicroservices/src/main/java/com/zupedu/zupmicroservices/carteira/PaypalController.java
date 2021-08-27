package com.zupedu.zupmicroservices.carteira;

import com.zupedu.zupmicroservices.cartao.CartaoClient;
import com.zupedu.zupmicroservices.validators.handlers.ValidationErrorsOutputDto;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/paypal")
public class PaypalController {
    private final CarteiraRepository carteiraRepository;
    private final CartaoClient cartaoClient;

    public PaypalController(CarteiraRepository carteiraRepository, CartaoClient cartaoClient) {
        this.carteiraRepository = carteiraRepository;
        this.cartaoClient = cartaoClient;
    }

    @PostMapping("/{idCartao}")
    @Transactional
    public ResponseEntity addToPaypal(@PathVariable String idCartao, @Valid @RequestBody PaypalForm paypalForm, HttpServletRequest request){
        Paypal paypal = paypalForm.toModel(idCartao);
        Optional<Paypal> carteiraBuscada = carteiraRepository.findByCartaoId(idCartao);
        if(idCartao == null || idCartao == ""){return ResponseEntity.badRequest().body(new ValidationErrorsOutputDto("Numero cartão","O id do cartçao é obrigatório")); }
        if(carteiraBuscada.isPresent()){ return ResponseEntity.unprocessableEntity().body(new ValidationErrorsOutputDto("cartaoId","Já existe uma carteira associada à esse cartão")); }
        try{
            var retorno = cartaoClient.associaCarteira(idCartao,paypal.toCarteiraRequest());
            carteiraRepository.save(paypal);
            URI uriRetorno = URI.create(request.getRequestURI().toString()+"/"+paypal.getId());
            return ResponseEntity.created(uriRetorno).body(retorno);
        }catch(FeignException e){
            return ResponseEntity.notFound().build();
        }
    }
}
