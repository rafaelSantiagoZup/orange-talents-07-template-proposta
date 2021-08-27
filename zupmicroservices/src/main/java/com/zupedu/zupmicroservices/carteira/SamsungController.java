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
import java.util.Optional;

@RestController
@RequestMapping("/api/samsung")
public class SamsungController {
    private final SamsungRepository carteiraRepository;
    private final CartaoClient cartaoClient;

    public SamsungController(SamsungRepository carteiraRepository, CartaoClient cartaoClient) {
        this.carteiraRepository = carteiraRepository;
        this.cartaoClient = cartaoClient;
    }

    @PostMapping("/{idCartao}")
    @Transactional
    public ResponseEntity addToSamsung(@PathVariable String idCartao, @Valid @RequestBody SamsungForm samsungForm, HttpServletRequest request){
        Samsung samsung = samsungForm.toModel(idCartao);
        Optional<Paypal> carteiraBuscada = carteiraRepository.findByCartaoId(idCartao);
        if(idCartao == null || idCartao == ""){return ResponseEntity.badRequest().body(new ValidationErrorsOutputDto("Numero cartão","O id do cartçao é obrigatório")); }
        if(carteiraBuscada.isPresent()){ return ResponseEntity.unprocessableEntity().body(new ValidationErrorsOutputDto("cartaoId","Já existe uma carteira associada à esse cartão")); }
        try{
            var retorno = cartaoClient.associaCarteira(idCartao,samsung.toCarteiraRequest());
            carteiraRepository.save(samsung);
            URI uriRetorno = URI.create(request.getRequestURI().toString()+"/"+samsung.getId());
            return ResponseEntity.created(uriRetorno).body(retorno);
        }catch(FeignException e){
            return ResponseEntity.notFound().build();
        }
    }
}
