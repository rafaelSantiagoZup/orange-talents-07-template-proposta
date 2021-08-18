package com.zupedu.zupmicroservices.proposta;

import com.zupedu.zupmicroservices.validators.annotations.CpfOuCnpj;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Length(min = 11, max = 13)
    @CpfOuCnpj
    private String documento;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @Positive
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String cartao;

    @Deprecated
    public Proposta() {
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Proposta(@NotBlank String documento, @NotBlank String nome, @NotBlank String endereco, @Positive BigDecimal salario) {
        this.documento = documento;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public SolicitacaoAnaliseForm toAnaliseForm(){
        return new SolicitacaoAnaliseForm(this.documento,this.nome,this.id.toString());
    }
}
