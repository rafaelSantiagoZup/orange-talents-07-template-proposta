package com.zupedu.zupmicroservices.cartao;

import java.time.LocalDateTime;

public class AvisoViagem {
    private LocalDateTime validoAte;
    private String destino;

    public AvisoViagem() {
    }

    public AvisoViagem(LocalDateTime validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public LocalDateTime getValidoAte() {
        return validoAte;
    }

    public void setValidoAte(LocalDateTime validoAte) {
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
