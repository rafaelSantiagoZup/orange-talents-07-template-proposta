package com.zupedu.zupmicroservices.viagem;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Entity
public class Viagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank
    private String cartaoId;
    @NotBlank
    private String destino;
    @FutureOrPresent
    @NotNull
    private LocalDate termino;
    private LocalDateTime instanteAviso = LocalDateTime.now();
    @NotBlank
    private String ipCliente;
    @NotBlank
    private String userAgent;
    private UUID identificadorViagem;

    @Deprecated
    public Viagem() {
    }

    public Viagem(String cartaoId, String destino, LocalDate termino, String ipCliente, String userAgent) {
        this.cartaoId = cartaoId;
        this.destino = destino;
        this.termino = termino;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.identificadorViagem = new UUID(new Random().nextLong(),new Random().nextLong());
    }
    public Long getId() {
        return id;
    }

    public ViagemDTO toDTO() {
        return new ViagemDTO(this.id,this.destino,this.termino,this.identificadorViagem);
    }
}
