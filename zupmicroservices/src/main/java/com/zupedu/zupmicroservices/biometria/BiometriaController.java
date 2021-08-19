package com.zupedu.zupmicroservices.biometria;

import com.zupedu.zupmicroservices.cartao.CartaoClient;
import com.zupedu.zupmicroservices.validators.handlers.ValidationErrorsOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class BiometriaController {

    private final BiometriaRepository biometriaRepository;
    private final CartaoClient cartaoClient;

    public BiometriaController(BiometriaRepository biometriaRepository, CartaoClient cartaoClient) {
        this.biometriaRepository = biometriaRepository;
        this.cartaoClient = cartaoClient;
    }

    @PostMapping("/biometria/{idCartao}")
    @Transactional
    public ResponseEntity addBiometria(@PathVariable String idCartao,@RequestBody BiometriaForm form, HttpServletRequest request){
        if(form.existsCartao(idCartao,cartaoClient)){
            if(form.isBase64()){
                Biometria biometria = form.toModel(idCartao);
                biometriaRepository.save(biometria);
                URI uriRetorno = URI.create(request.getRequestURI().toString()+"/"+biometria.getId());
                return ResponseEntity.created(uriRetorno).body("Biometria cadastrada com sucesso!");
            }
            return ResponseEntity.badRequest().body(new ValidationErrorsOutputDto("fingerprint","O campo deve contar um valor em base64!"));
        }
        return ResponseEntity.notFound().build();
    }


}
