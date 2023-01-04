package pl.gajdek.alekino.exceptions;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = CustomDateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomDateConstraint {
    String message() default "Invalid date format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
