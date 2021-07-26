package br.com.zupacademy.gustavo.proposta.annotation;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IsBase64Validator.class})
public @interface IsBase64 {

    String message() default "A impressão digital não está em Base64.";
}
