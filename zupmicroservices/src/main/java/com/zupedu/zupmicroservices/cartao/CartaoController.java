package com.zupedu.zupmicroservices.cartao;

import com.zupedu.zupmicroservices.proposta.Proposta;
import com.zupedu.zupmicroservices.proposta.PropostaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CartaoController {
    private final CartaoClient cartaoClient;
    private final PropostaRepository propostaRepository;
    private final BloqueioRepository bloqueioRepository;

    public CartaoController(CartaoClient cartaoClient, PropostaRepository propostaRepository, BloqueioRepository bloqueioRepository) {
        this.cartaoClient = cartaoClient;
        this.propostaRepository = propostaRepository;
        this.bloqueioRepository = bloqueioRepository;
    }
    @PostMapping("/bloqueio/{id}")
    @Transactional
    public ResponseEntity bloqueiaCartao(@PathVariable String id,@RequestHeader(value="User-Agent") String userAgent){
        if(id == null || id == ""){
            return ResponseEntity.badRequest().build();
        }
        Optional<Bloqueio> bloqueioOpt = bloqueioRepository.findByCartaoId(id);
        if (!bloqueioOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Proposta> proposta = propostaRepository.findByCartao(id);
        if(proposta.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        try{
            var resposta = cartaoClient.bloqueiaCartao(id,new ReqBloqueioForm(userAgent));
            Bloqueio bloqueios = new Bloqueio(id,userAgent,resposta.getResultado());
            bloqueioRepository.save(bloqueios);
            return ResponseEntity.ok().body("Cartão bloqueado com sucesso");
        }catch (Exception e){
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
