package com.petersonexercicio.course.services;

import com.petersonexercicio.course.dto.request.create.CategoryRequestDTO;
import com.petersonexercicio.course.dto.response.CategoryResponseDTO;
import com.petersonexercicio.course.entities.Category;
import com.petersonexercicio.course.repositories.CategoryRepository;
import com.petersonexercicio.course.services.exceptions.ResourceNotFoundException;
import com.petersonexercicio.course.services.mapper.CategoryMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public Set<CategoryResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toSet());
    }

    public CategoryResponseDTO findById(Long id){
        Category entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return mapper.toResponse(entity);
    }

    public CategoryResponseDTO insert(CategoryRequestDTO request){
        Category entity = repository.save(mapper.toEntity(request));
        return mapper.toResponse(entity);
    }

    public CategoryResponseDTO update(Long id, CategoryResponseDTO request){

        if(!repository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }
        Category entity = repository.getReferenceById(id);
        entity.setName(request.name());
        return mapper.toResponse(repository.save(entity));
    }

    public void delete(Long id){
        if(!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
