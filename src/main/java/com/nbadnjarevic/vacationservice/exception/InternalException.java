package com.nbadnjarevic.vacationservice.exception;

public class InternalException extends RuntimeException {

    public InternalException(String pattern, Object... args) {
        super(String.format(pattern, args), extractCause(args));
    }

    static Throwable extractCause(Object... args) {
        if (args == null || args.length == 0) {
            return null;
        }
        if (args[args.length - 1] instanceof Throwable) {
            return (Throwable) args[args.length - 1];
        }
        return null;
    }

}