package com.zupedu.zupmicroservices.carteira;

import com.zupedu.zupmicroservices.validators.annotations.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PaypalForm {

    @NotBlank
    @Email
    private String email;

    public PaypalForm() {
    }

    public PaypalForm(String email) {
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Paypal toModel(String cartaoId) {
        return new Paypal(cartaoId,email);
    }
}
