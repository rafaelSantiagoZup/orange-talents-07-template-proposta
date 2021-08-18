package com.zupedu.zupmicroservices.cartao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cartao {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<Bloqueios> bloqueios= new ArrayList<Bloqueios>();
    private List<AvisoViagem> avisos = new ArrayList<AvisoViagem>();
    private List<Cateiras> cateiras = new ArrayList<Cateiras>();
    private List<Parcelas> parcelas = new ArrayList<Parcelas>();
    private Integer limite;
    private Renegociacao renegociacao;
    private Vencimento vencimento;
    private String idProposta;

    public Cartao() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public void setEmitidoEm(LocalDateTime emitidoEm) {
        this.emitidoEm = emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public List<Bloqueios> getBloqueios() {
        return bloqueios;
    }

    public void setBloqueios(List<Bloqueios> bloqueios) {
        this.bloqueios = bloqueios;
    }

    public List<AvisoViagem> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<AvisoViagem> avisos) {
        this.avisos = avisos;
    }

    public List<Cateiras> getCateiras() {
        return cateiras;
    }

    public void setCateiras(List<Cateiras> cateiras) {
        this.cateiras = cateiras;
    }

    public List<Parcelas> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcelas> parcelas) {
        this.parcelas = parcelas;
    }

    public Integer getLimite() {
        return limite;
    }

    public void setLimite(Integer limite) {
        this.limite = limite;
    }

    public Renegociacao getRenegociacao() {
        return renegociacao;
    }

    public void setRenegociacao(Renegociacao renegociacao) {
        this.renegociacao = renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public void setVencimento(Vencimento vencimento) {
        this.vencimento = vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(String idProposta) {
        this.idProposta = idProposta;
    }

    public Cartao(String id, LocalDateTime emitidoEm, String titular, List<Bloqueios> bloqueios, List<AvisoViagem> avisos, List<Cateiras> cateiras, List<Parcelas> parcelas, Integer limite, Renegociacao renegociacao, Vencimento vencimento, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.cateiras = cateiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }
}
