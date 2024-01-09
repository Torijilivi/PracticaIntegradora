package validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.matorija.cookies.model.Usuario;

public class UsuarioValidador implements ConstraintValidator<ExisteUsuario, Usuario> {
    @Override
    public boolean isValid(Usuario value, ConstraintValidatorContext context) {
        return false;
    }
}
