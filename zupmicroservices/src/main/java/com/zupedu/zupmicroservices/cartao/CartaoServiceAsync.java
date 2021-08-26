package com.zupedu.zupmicroservices.cartao;

import com.zupedu.zupmicroservices.configuration.MinhasMetricas;
import com.zupedu.zupmicroservices.proposta.Proposta;
import com.zupedu.zupmicroservices.proposta.PropostaRepository;
import com.zupedu.zupmicroservices.proposta.Status;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class CartaoServiceAsync {


    private final CartaoClient cartaoClient;
    private final PropostaRepository propostaRepository;
    private final MinhasMetricas minhasMetricas;

    public CartaoServiceAsync(CartaoClient cartaoClient, PropostaRepository propostaRepository, MinhasMetricas minhasMetricas) {
        this.cartaoClient = cartaoClient;
        this.propostaRepository = propostaRepository;
        this.minhasMetricas = minhasMetricas;
    }

    @Scheduled(fixedDelayString = "${tempo.espera.thread}")
    @Transactional
    public void addCartaoAsync() {

        List<Proposta> propostas = propostaRepository.findAll();
        propostas.stream().forEach(proposta->{
            if(!proposta.pussuiCartao() && proposta.getStatus()== Status.ELEGIVEL){
                String cartao = cartaoClient.addCartao(proposta.toAnaliseForm()).getId();
                proposta.setCartao(cartao);
                propostaRepository.save(proposta);
                minhasMetricas.meuContadorCartoes(proposta.getId());
            }
        });



    }

}
