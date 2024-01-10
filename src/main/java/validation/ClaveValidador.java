package validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.matorija.cookies.model.Usuario;

public class ClaveValidador implements ConstraintValidator<CoincidenciaClaves, Usuario> {

    @Override
    public boolean isValid(Usuario usuario, ConstraintValidatorContext c){
        String clave = usuario.getClave();
        String confirmarClave = usuario.getConfirmarClave();
        return clave.equals(confirmarClave);
    }

    @Override
    public void initialize(CoincidenciaClaves constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
