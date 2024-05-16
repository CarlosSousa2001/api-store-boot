package com.crs.datajpa.exceptions;

public class HandleGenericNotFound extends RuntimeException {

    public HandleGenericNotFound(Long id) {
        super("---------------NOT FOUND----------");
    }
}
