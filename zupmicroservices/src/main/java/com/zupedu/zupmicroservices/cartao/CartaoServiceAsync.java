package com.zupedu.zupmicroservices.cartao;

import com.zupedu.zupmicroservices.proposta.Proposta;
import com.zupedu.zupmicroservices.proposta.PropostaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class CartaoServiceAsync {


    private final CartaoClient cartaoClient;
    private final PropostaRepository propostaRepository;

    public CartaoServiceAsync(CartaoClient cartaoClient, PropostaRepository propostaRepository) {
        this.cartaoClient = cartaoClient;
        this.propostaRepository = propostaRepository;
    }

    @Scheduled(fixedDelayString = "${tempo.espera.thread}")
    @Transactional
    public void addCartaoAsync() {

        List<Proposta> propostas = propostaRepository.findAll();
        propostas.stream().forEach(proposta->{
            if(!proposta.pussuiCartao()){
                String cartao = cartaoClient.addCartao(proposta.toAnaliseForm()).getId();
                proposta.setCartao(cartao);
                propostaRepository.save(proposta);
            }
        });



    }

}
