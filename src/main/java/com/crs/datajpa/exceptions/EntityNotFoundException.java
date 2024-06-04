package com.crs.datajpa.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super("item não encontrado");
    }
}
