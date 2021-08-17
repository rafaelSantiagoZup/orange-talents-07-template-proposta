package com.zupedu.zupmicroservices.validators.annotations;

import com.zupedu.zupmicroservices.validators.classes.CPFouCNPJValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CPFouCNPJValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfOuCnpj {
    String message() default "CPF ou CNPJ inválido!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
