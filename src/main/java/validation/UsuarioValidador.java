package validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static org.matorija.cookies.model.Colecciones.*;

public class UsuarioValidador implements ConstraintValidator<ExisteUsuario, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (existeNombre(value)){
            return false;
        }
        return true;
    }
}
