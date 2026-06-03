package com.petersonexercicio.course.services.mapper;

import com.petersonexercicio.course.dto.request.create.ProductRequestDTO;
import com.petersonexercicio.course.dto.response.ProductResponseDTO;
import com.petersonexercicio.course.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDTO dto){

        return new Product(
                null,
                dto.name(),
                dto.description(),
                dto.price(),
                dto.imgUrl()
        );
    }

    public ProductResponseDTO toResponse(Product product){
        return null;
    }
}
