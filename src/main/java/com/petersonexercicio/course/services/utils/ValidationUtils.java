package com.petersonexercicio.course.services.utils;

import com.petersonexercicio.course.services.exceptions.ResourceNotFoundException;

public class ValidationUtils {

    public static void checkId(Long id){
        if (id == null || id <= 0){
            throw new ResourceNotFoundException(id);
        }

    }

}
