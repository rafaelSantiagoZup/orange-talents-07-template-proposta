package com.zupedu.zupmicroservices.proposta;

public class PropostaDto {
    private String nome;
    private String endereco;
    private Status status;
    private boolean possuiCartao;

    public PropostaDto( String nome, String endereco, Status status, boolean possuiCartao) {
        this.nome = nome;
        this.endereco = endereco;
        this.status = status;
        this.possuiCartao = possuiCartao;
    }

    @Deprecated
    public PropostaDto() {
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isPossuiCartao() {
        return possuiCartao;
    }
}
