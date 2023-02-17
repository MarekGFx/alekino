package pl.gajdek.alekino.enums.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValidateEnum, String> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValidateEnum constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        boolean isValid = false;
        for (Enum<?> enumValue : enumClass.getEnumConstants()) {
            if (enumValue.toString().equals(value)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
