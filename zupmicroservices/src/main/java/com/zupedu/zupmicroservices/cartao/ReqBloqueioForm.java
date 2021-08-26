package com.zupedu.zupmicroservices.cartao;

public class ReqBloqueioForm {
    private String sistemaResponsavel;

    public ReqBloqueioForm(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public ReqBloqueioForm() {
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
