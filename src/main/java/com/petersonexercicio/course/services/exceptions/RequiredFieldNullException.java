package com.petersonexercicio.course.services.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class RequiredFieldNullException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public RequiredFieldNullException(String campo) {
        super("O campo '" + campo + "' não pode ser nulo.");
    }
}
