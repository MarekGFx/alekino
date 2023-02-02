package pl.gajdek.alekino.exceptions.exceptionHandler;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.gajdek.alekino.exceptions.UniqueDataConstraintException;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UniqueDataConstraintException.class)
    public Map<String, String> handleBusinessException(UniqueDataConstraintException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public Map<String, String> handleBusinessException(NoSuchElementException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeParseException.class)
    public Map<String, String> handleBusinessException(DateTimeParseException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", "date: " + ex.getParsedString()
                + " is incorrect, give the date according to the pattern yyyy-MM-dd");
        return errorMap;
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(InvalidFormatException.class)
//    public Map<String, String> handleBusinessException(InvalidFormatException ex){
//        Map<String, String> errorMap = new HashMap<>();
//        errorMap.put("errorMessage", (String) ex.getValue() + " invalid data");
//        return errorMap;
//    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public Map<String, String> handleBusinessException(HttpMessageNotReadableException ex){
//        Map<String, String> errorMap = new HashMap<>();
//        errorMap.put("errorMessage", ex.getMessage());
//        return errorMap;
//    }
}
