package com.zupedu.zupmicroservices.cartao;

import javax.validation.constraints.NotBlank;

public class RespostaBloqueioForm {
    @NotBlank
    private String resultado;

    @Deprecated
    public RespostaBloqueioForm() {
    }

    public RespostaBloqueioForm(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
