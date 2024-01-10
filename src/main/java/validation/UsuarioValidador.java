package validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.matorija.cookies.model.Usuario;

import static org.matorija.cookies.model.Colecciones.*;

public class UsuarioValidador implements ConstraintValidator<ExisteUsuario, Usuario> {
    @Override
    public boolean isValid(Usuario usuario, ConstraintValidatorContext c) {
        if (usuarios.isEmpty()) {
            return true;
        }
        String nombre = usuario.getNombre();
        for (Usuario user : usuarios) {
            if (user.getNombre().equals(nombre)) {
                return false;
            }
        }
        return true;
    }
}
