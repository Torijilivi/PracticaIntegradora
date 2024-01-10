package validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.matorija.cookies.model.Usuario;

import java.time.LocalDate;
import java.time.Period;

public class FechaValidador implements ConstraintValidator<FechaMayorEdad, LocalDate> {

    @Override
    public boolean isValid(LocalDate fechaNacimiento, ConstraintValidatorContext c){
        if (fechaNacimiento == null){
            return true;
        }
        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }
}
