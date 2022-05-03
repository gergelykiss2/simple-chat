package com.gergelytamas.simplechat.exception;

public class MissingIdException extends RuntimeException {

    public MissingIdException() {
        super("Missing ID!");
    }

}
