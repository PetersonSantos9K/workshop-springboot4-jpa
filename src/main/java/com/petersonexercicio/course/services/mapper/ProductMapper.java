package com.petersonexercicio.course.services.mapper;

import com.petersonexercicio.course.dto.request.create.ProductRequestDTO;
import com.petersonexercicio.course.dto.response.CategoryResponseDTO;
import com.petersonexercicio.course.dto.response.ProductResponseDTO;
import com.petersonexercicio.course.entities.Product;

import org.springframework.stereotype.Component;
import java.util.Set;

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

    public ProductResponseDTO toResponse(Product product, Set<CategoryResponseDTO> categories){
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImgUrl(),
                categories
        );
    }
}
