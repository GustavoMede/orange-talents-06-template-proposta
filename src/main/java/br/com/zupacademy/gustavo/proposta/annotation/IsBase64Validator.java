package br.com.zupacademy.gustavo.proposta.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

public class IsBase64Validator implements ConstraintValidator<IsBase64, String> {
    @Override
    public void initialize(IsBase64 constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        try{
            Base64.getDecoder().decode(value.getBytes());
            return true;
        }catch(IllegalArgumentException ex){
            return false;
        }
    }
}
