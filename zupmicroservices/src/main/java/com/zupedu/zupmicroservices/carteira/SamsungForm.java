package com.zupedu.zupmicroservices.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SamsungForm {

    @NotBlank
    @Email
    private String email;

    public SamsungForm() {
    }

    public SamsungForm(String email) {
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Samsung toModel(String cartaoId) {
        return new Samsung(cartaoId,email);
    }
}
