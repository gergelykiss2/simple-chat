package com.gergelytamas.simplechat.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Item not found!");
    }
}
