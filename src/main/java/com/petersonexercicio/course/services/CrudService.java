package com.petersonexercicio.course.services;

import java.util.List;

public interface CrudService <DTOResponse, DtoRequest>{

    public List<DTOResponse> findAll();
    public DTOResponse findById(Long id);
    public DTOResponse insert(DtoRequest obj);
    public DTOResponse update(Long id, DtoRequest obj);
    public void delete(Long id);
}
