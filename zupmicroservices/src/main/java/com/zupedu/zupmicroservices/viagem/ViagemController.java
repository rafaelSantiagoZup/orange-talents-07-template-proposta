package com.zupedu.zupmicroservices.viagem;

import com.zupedu.zupmicroservices.cartao.CartaoClient;
import com.zupedu.zupmicroservices.validators.handlers.ValidationErrorsOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/viagem")
public class ViagemController {
    private final ViagemRepository viagemRepository;
    private final CartaoClient cartaoClient;

    public ViagemController(ViagemRepository viagemRepository, CartaoClient cartaoClient) {
        this.viagemRepository = viagemRepository;
        this.cartaoClient = cartaoClient;
    }
    @PostMapping("/{idCartao}")
    @Transactional
    public ResponseEntity addViagem(@PathVariable String idCartao,
                                    @RequestHeader(name = "Host") String ipCliente,
                                    @RequestHeader(name = "User-Agent") String userAgent,
                                    @Valid @RequestBody ViagemForm viagemForm){
        if(idCartao == null || idCartao == "" || viagemForm.getTermino().isBefore(LocalDate.now())){
            return ResponseEntity.badRequest().body(new ValidationErrorsOutputDto("todos","Todos os campos são obrigatórios e a data e término deve estar no futuro!"));
        }
        try{
            Viagem viagem = viagemForm.toModel(idCartao,ipCliente,userAgent);
            cartaoClient.avisaViagem(idCartao,new ViagemReqForm(viagemForm));
            viagemRepository.save(viagem);
            ViagemDTO dto = viagem.toDTO();
            return ResponseEntity.ok(dto);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
