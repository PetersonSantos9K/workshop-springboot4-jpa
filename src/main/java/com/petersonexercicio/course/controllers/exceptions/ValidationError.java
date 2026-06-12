package com.petersonexercicio.course.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;


@Getter
@Setter
public class ValidationError implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant timestamp;
    private Integer status;
    private Map<String, String> errors = new LinkedHashMap<>();
    private String message;
    private String path;

    public ValidationError(Instant timestamp, Integer status, Map<String, String> errors, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.errors = errors;
        this.message = message;
        this.path = path;
    }
}
