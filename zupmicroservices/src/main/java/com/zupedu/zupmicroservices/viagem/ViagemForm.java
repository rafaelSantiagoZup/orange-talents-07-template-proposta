package com.zupedu.zupmicroservices.viagem;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ViagemForm {
    @NotBlank
    private String destino;
    @FutureOrPresent
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate termino;

    @Deprecated
    public ViagemForm() {
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getTermino() {
        return termino;
    }

    public ViagemForm(String destino, LocalDate termino) {
        this.destino = destino;
        this.termino = termino;
    }

    public Viagem toModel(String cartaoId,String ipCliente,String userAgent){
        return new Viagem(cartaoId,this.destino,this.termino,ipCliente,userAgent);
    }
}
