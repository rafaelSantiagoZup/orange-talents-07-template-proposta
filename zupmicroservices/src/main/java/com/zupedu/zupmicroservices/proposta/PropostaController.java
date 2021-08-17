package com.zupedu.zupmicroservices.proposta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/proposta")
public class PropostaController {
    private final PropostaRepository propostaRepository;

    public PropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @PostMapping
    public ResponseEntity addProposta(@Valid @RequestBody PropostaForm propostaForm, HttpServletRequest request){
        Proposta proposta = propostaForm.toModel();
        propostaRepository.save(proposta);
        URI uriRetorno = URI.create(request.getRequestURI().toString()+"/"+proposta.getId());
        return ResponseEntity.created(uriRetorno).build();
    }
}
