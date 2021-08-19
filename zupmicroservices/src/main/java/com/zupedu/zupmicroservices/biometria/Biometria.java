package com.zupedu.zupmicroservices.biometria;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Biometria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataCadastro = LocalDateTime.now();
    @NotBlank
    private String cartaoId;
    @NotBlank
    @Column(length = 10000)
    private String fingerprint;

    public Biometria() {
    }

    public Long getId() {
        return id;
    }

    public Biometria(String cartaoId, String fingerprint) {
        this.cartaoId = cartaoId;
        this.fingerprint = fingerprint;
    }
}
