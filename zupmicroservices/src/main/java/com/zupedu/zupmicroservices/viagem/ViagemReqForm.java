package com.zupedu.zupmicroservices.viagem;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ViagemReqForm {
    @NotBlank
    private String destino;
    @FutureOrPresent
    @NotNull
    private LocalDate validoAte;

    @Deprecated
    public ViagemReqForm() {
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public ViagemReqForm(ViagemForm viagemForm) {
        this.destino = viagemForm.getDestino();
        this.validoAte = viagemForm.getTermino();
    }
}
