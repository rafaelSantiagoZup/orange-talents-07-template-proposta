package com.zupedu.zupmicroservices.viagem;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public class ViagemDTO {
    private Long id;
    private String destino;
    private LocalDate termino;
    private UUID identificadorViagem;

    public Long getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getTermino() {
        return termino;
    }

    public UUID getIdentificadorViagem() {
        return identificadorViagem;
    }

    public ViagemDTO(Long id, String destino, LocalDate termino, UUID identificadorViagem) {
        this.id = id;
        this.destino = destino;
        this.termino = termino;
        this.identificadorViagem = identificadorViagem;
    }
}
