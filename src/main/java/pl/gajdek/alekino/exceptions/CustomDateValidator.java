package pl.gajdek.alekino.exceptions;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class CustomDateValidator implements
        ConstraintValidator<CustomDateConstraint, LocalDate> {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    @Override
    public void initialize(CustomDateConstraint customDate) {
    }

    @Override
    public boolean isValid(LocalDate customDateField,
                           ConstraintValidatorContext cxt) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        try
        {

            sdf.setLenient(false);
            Date d = sdf.parse(String.valueOf(customDateField));
            return true;
        }
        catch (ParseException e)
        {
            return false;
        }
    }

}
