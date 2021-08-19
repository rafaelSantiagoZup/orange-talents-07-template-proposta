package com.zupedu.zupmicroservices.biometria;

import com.zupedu.zupmicroservices.cartao.Cartao;
import com.zupedu.zupmicroservices.cartao.CartaoClient;
import feign.FeignException;

import javax.validation.constraints.NotBlank;
import java.util.regex.Pattern;

public class BiometriaForm {
    @NotBlank
    private String fingerprint;

    public String getFingerprint() {
        return fingerprint;
    }

    public BiometriaForm(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public BiometriaForm() {
    }

    public Biometria toModel(String idCartao) {
        return new Biometria(idCartao,this.fingerprint);
    }

    public boolean existsCartao(String idCartao, CartaoClient cartaoClient) {

        try{
            Cartao cartao = cartaoClient.getCartao(idCartao);
            System.out.println(cartao.getId());
            return true;
        }catch (FeignException.FeignClientException e){
            return false;
        }
    }

    public boolean isBase64() {
        var base64Regex = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$";
        return Pattern.matches(base64Regex,this.fingerprint);
    }
}
