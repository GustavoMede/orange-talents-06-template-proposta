package br.com.zupacademy.gustavo.proposta.annotation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CampoDuplicadoValidator implements ConstraintValidator<CampoDuplicado, Object> {

    @PersistenceContext
    EntityManager entityManager;

    private String nomeCampo;

    private Class<?> nomeClasse;

    @Override
    public void initialize(CampoDuplicado constraintAnnotation) {
        nomeCampo = constraintAnnotation.fieldName();
        nomeClasse = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if(nomeCampo.equals("documento")){
            List<Boolean> valida = new ArrayList<>();
            System.out.println("Entrando na validação");
            Query query = entityManager.createQuery("Select documento from "+ nomeClasse.getName());
            List<?> resultadoConsulta = query.getResultList();
            resultadoConsulta.forEach(resultado -> {
                 valida.addAll(Collections.singletonList(new BCryptPasswordEncoder().matches(value.toString(), resultado.toString())));
            });
            for(int i = 0; i < valida.size(); i++){
                if(valida.contains(true)){
                    throw HttpClientErrorException.UnprocessableEntity.create(HttpStatus.UNPROCESSABLE_ENTITY,
                            "Proposta já realizada para esse documento.", HttpHeaders.EMPTY,
                            null, null);
                }
            }
            return true;
        }
        System.out.println("Entrando na validação.");
        Query query = entityManager.createQuery("Select 1 from "+ nomeClasse.getName() +" where "+ nomeCampo +"=:value");
        query.setParameter("value", value);
        List<?> resultadoConsulta = query.getResultList();
        return resultadoConsulta.isEmpty();
    }
}
