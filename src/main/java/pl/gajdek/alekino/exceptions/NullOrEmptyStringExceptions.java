package pl.gajdek.alekino.exceptions;

public class NullOrEmptyStringExceptions extends RuntimeException{

    public NullOrEmptyStringExceptions(){}
    public NullOrEmptyStringExceptions(String message){
        super(message);
    }
}
