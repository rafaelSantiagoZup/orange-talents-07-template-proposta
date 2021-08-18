package com.zupedu.zupmicroservices.proposta;

import javax.validation.constraints.NotBlank;

public class StatusServiceResponseDTO {
    @NotBlank
    private String documento;
    @NotBlank
    private String nome;
    @NotBlank
    private StatusFromServer resultadoSolicitacao;
    @NotBlank
    private String idProposta;

    @Deprecated
    public StatusServiceResponseDTO() {
    }

    public StatusServiceResponseDTO(String documento, String nome, StatusFromServer resultadoSolicitacao, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public StatusFromServer getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }

    @Override
    public String toString() {
        return "StatusServiceResponseDTO{" +
                "documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", resultadoSolicitacao=" + resultadoSolicitacao +
                ", idProposta='" + idProposta + '\'' +
                '}';
    }
}
