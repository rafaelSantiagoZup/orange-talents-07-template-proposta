package com.zupedu.zupmicroservices.carteira;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Paypal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank
    private String cartaoId;
    @NotBlank
    @Email
    private String email;


    @Deprecated
    public Paypal() {
    }

    public Paypal(String cartaoId, String email) {
        this.cartaoId = cartaoId;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public CarteiraRequest toCarteiraRequest() {
        return new CarteiraRequest(email,"Paypal");
    }

    public PaypayDTO toPaypayDTO() {
        return null;
    }
}
