package com.zupedu.zupmicroservices.proposta;

import com.zupedu.zupmicroservices.validators.annotations.CpfOuCnpj;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaForm {
    @NotBlank
    @Length(min = 11, max = 13)
    @CpfOuCnpj
    private String documento;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @Positive
    private BigDecimal salario;

    @Deprecated
    public PropostaForm() {
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

    public BigDecimal getSalario() {
        return salario;
    }

    public PropostaForm(String documento, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel() {
        return new Proposta(this.documento,this.nome,this.endereco,this.salario);
    }

    public boolean isUnique(PropostaRepository propostaRepository) {
        return propostaRepository.findByDocumento(this.documento).isEmpty();
    }
}
