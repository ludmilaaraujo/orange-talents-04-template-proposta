package br.com.proposta.microservico.anotacoes;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {AtributoUnicoValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface AtributoUnico {
    String message() default "O atributo está repetido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };

    String fieldName();

    Class<?> domainClass();
}

