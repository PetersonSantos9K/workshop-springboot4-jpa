package com.petersonexercicio.course.services.mapper;

import com.petersonexercicio.course.dto.request.create.CategoryRequestDTO;
import com.petersonexercicio.course.dto.response.CategoryResponseDTO;
import com.petersonexercicio.course.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequestDTO request){
        return new Category(
                null,
                request.name()
        );
    }

    public CategoryResponseDTO toResponse(Category category){
        return new CategoryResponseDTO(
                category.getId(),
                category.getName()
        );
    }


}
