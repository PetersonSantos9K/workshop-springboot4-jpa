package com.petersonexercicio.course.services.exceptions;
import java.io.Serial;
import java.io.Serializable;

public class ResourceAlreadyRegistered extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceAlreadyRegistered(Object value){
        super("The " + value + " address is already registered.");
    }
}
