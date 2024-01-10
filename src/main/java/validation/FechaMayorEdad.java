package validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FechaValidador.class)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaMayorEdad {
    String message() default "{FechaMayorEdad.usuario.fechaNacimiento}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
