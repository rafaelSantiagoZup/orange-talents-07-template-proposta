package com.zupedu.zupmicroservices.validators.annotations;

import com.zupedu.zupmicroservices.validators.classes.UniqueValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ElementType.FIELD})
@Retention( RetentionPolicy.RUNTIME)
public @interface UniqueValue {
    String message() default "Foi encontrado mais de uma proposta para o mesmo cpf!";

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    String fieldName();
    Class<?> domainClass();
}
