package com.zupedu.zupmicroservices.cartao;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Parcelas {
    private String id;
    private Integer quantidade;
    private BigDecimal valor;

    public Parcelas() {
    }

    public Parcelas(String id, Integer quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
