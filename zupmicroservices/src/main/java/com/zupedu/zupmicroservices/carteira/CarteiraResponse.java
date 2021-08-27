package com.zupedu.zupmicroservices.carteira;

import javax.validation.constraints.NotBlank;

public class CarteiraResponse {
    @NotBlank
    private String resultado;
    @NotBlank
    private String id;

    @Deprecated
    public CarteiraResponse() {
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }

    public CarteiraResponse(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }
}
