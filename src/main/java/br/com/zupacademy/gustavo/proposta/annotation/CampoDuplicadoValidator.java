package br.com.zupacademy.gustavo.proposta.annotation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
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
        Query query = entityManager.createQuery("Select 1 from "+ nomeClasse.getName() +" where "+ nomeCampo +"=:value");
        query.setParameter("value", value);
        List<?> resultadoConsulta = query.getResultList();
        return resultadoConsulta.isEmpty();
    }
}
