package com.zupedu.zupmicroservices.cartao;

import com.zupedu.zupmicroservices.proposta.Proposta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CartaoServiceAsync {

    @Value("${tempo.espera.thread}")
    private Integer milisegundos;

    private final CartaoClient cartaoClient;

    public CartaoServiceAsync(CartaoClient cartaoClient) {
        this.cartaoClient = cartaoClient;
    }

    @Async
    public String addCartaoAsync(Proposta proposta) throws InterruptedException {
        System.out.println("Execute method asynchronously - "
                + Thread.currentThread().getName());
        AsyncResult<String> numero = new AsyncResult<String>(cartaoClient.addCartao(proposta.toAnaliseForm()).getId());
        String valor = "";
        try{
            System.out.println(milisegundos);
            Thread.sleep(milisegundos);
            valor = numero.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return valor;
    }

}
