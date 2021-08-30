package com.zupedu.zupmicroservices.proposta;

import com.zupedu.zupmicroservices.utils.DocumentsHandler;
import com.zupedu.zupmicroservices.validators.annotations.CpfOuCnpj;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta implements DocumentsHandler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(length = 255)
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
        this.documento = encriptaDados(documento);
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }
    public boolean pussuiCartao(){
        if(this.cartao != null){
            return true;
        }return false;
    }

    public String escondeNumeroDocumento(String documento){
        Integer size = documento.length();
        String retorno = "";
        for(int i = 0;i<size-3;i++){
            retorno+="*";
        }
        for(int j=size-3;j<size;j++){
            retorno+= documento.charAt(j);
        }
        return retorno;
    }

    public PropostaDto toPropostaDto(){
        return new PropostaDto(this.nome,this.endereco,this.status,pussuiCartao());
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public SolicitacaoAnaliseForm toAnaliseForm(){
        return new SolicitacaoAnaliseForm(this.documento,this.nome,this.id.toString());
    }

    public Status getStatus() {
        return status;
    }
    public String encriptaDados(String documento){
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        return bCrypt.encode(documento);
    }

    @Override
    public boolean checaDocumento(String documento) {
        String enc = encriptaDados(documento);
        if(enc.equals(this.documento)){
            return true;
        }
        return false;
    }
}
