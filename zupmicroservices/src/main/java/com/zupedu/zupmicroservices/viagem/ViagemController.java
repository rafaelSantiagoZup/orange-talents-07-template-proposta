package com.zupedu.zupmicroservices.viagem;

import com.zupedu.zupmicroservices.validators.handlers.ValidationErrorsOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/viagem")
public class ViagemController {
    private final ViagemRepository viagemRepository;

    public ViagemController(ViagemRepository viagemRepository) {
        this.viagemRepository = viagemRepository;
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
            viagemRepository.save(viagem);
            ViagemDTO dto = viagem.toDTO();
            return ResponseEntity.ok(dto);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
