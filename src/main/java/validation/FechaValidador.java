package validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.matorija.cookies.model.Usuario;

import java.time.LocalDate;
import java.time.Period;

public class FechaValidador implements ConstraintValidator<FechaMayorEdad, Usuario> {

    @Override
    public boolean isValid(Usuario usuario, ConstraintValidatorContext c){
        LocalDate fechaNacimiento = usuario.getFechaNacimiento();
        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }
}
