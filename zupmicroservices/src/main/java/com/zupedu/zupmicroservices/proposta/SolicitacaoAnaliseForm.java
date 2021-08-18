package com.zupedu.zupmicroservices.proposta;

import javax.validation.constraints.NotBlank;

public class SolicitacaoAnaliseForm {
    @NotBlank
    private String documento;
    private String nome;
    private String idProposta;

    @Deprecated
    public SolicitacaoAnaliseForm() {
    }

    public SolicitacaoAnaliseForm(String documento, String nome, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
