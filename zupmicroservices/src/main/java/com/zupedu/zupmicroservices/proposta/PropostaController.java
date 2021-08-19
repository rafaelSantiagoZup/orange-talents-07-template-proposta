package com.zupedu.zupmicroservices.proposta;

import com.zupedu.zupmicroservices.cartao.CartaoClient;
import com.zupedu.zupmicroservices.cartao.CartaoServiceAsync;
import com.zupedu.zupmicroservices.validators.handlers.ValidationErrorsOutputDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/proposta")
public class PropostaController {

    private final PropostaRepository propostaRepository;
    private final StatusClient statusClient;

    public PropostaController(PropostaRepository propostaRepository, StatusClient statusClient, CartaoClient cartaoClient) {
        this.propostaRepository = propostaRepository;
        this.statusClient = statusClient;
    }


    @PostMapping
    @Transactional
    public ResponseEntity addProposta(@Valid @RequestBody PropostaForm propostaForm, HttpServletRequest request) throws InterruptedException, ExecutionException {

        if(!propostaForm.isUnique(propostaRepository)){
            return ResponseEntity.status(HttpStatus.valueOf(422)).body(new ValidationErrorsOutputDto
                    ("documento","Só pode haver uma proposta por Documento!"));
        }

        Proposta proposta = propostaForm.toModel();
        propostaRepository.save(proposta);
        SolicitacaoAnaliseForm form = proposta.toAnaliseForm();

        try{
            StatusServiceResponseDTO propostaStatus = statusClient.addSolicitacao(form);
            proposta.setStatus(propostaStatus.getResultadoSolicitacao().statusElegibilidade());
        }catch (Exception e){
            proposta.setStatus(Status.NAO_ELEGIVEL);
            return ResponseEntity.status(HttpStatus.valueOf(422)).body(new ValidationErrorsOutputDto("documento","A análise desse documento retornou uma restrição!"));
        }

        URI uriRetorno = URI.create(request.getRequestURI().toString()+"/"+proposta.getId());
        return ResponseEntity.created(uriRetorno).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropostaDto> getProposta(@PathVariable Long id){
        Optional<Proposta> proposta = propostaRepository.findById(id);
        return ResponseEntity.ok().body(proposta.get().toPropostaDto());
    }
}
