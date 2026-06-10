package com.petersonexercicio.course.services;
import com.petersonexercicio.course.dto.request.create.ProductRequestDTO;
import com.petersonexercicio.course.dto.request.update.ProductUpdateRequestDTO;
import com.petersonexercicio.course.dto.response.CategoryResponseDTO;
import com.petersonexercicio.course.dto.response.ProductResponseDTO;
import com.petersonexercicio.course.entities.Product;
import com.petersonexercicio.course.repositories.ProductRepository;
import com.petersonexercicio.course.services.exceptions.ResourceNotFoundException;
import com.petersonexercicio.course.services.mapper.CategoryMapper;
import com.petersonexercicio.course.services.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService{

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final CategoryMapper categoryMapper;

    public List<ProductResponseDTO> findAll(){

        List<Product> products = repository.findAll();
        return products.stream()
                .map(p -> {
                    Set<CategoryResponseDTO> productCategories = p.getCategories().stream()
                            .map(categoryMapper::toResponse)
                            .collect(Collectors.toSet());
                    return mapper.toResponse(p, productCategories);
                })
                .collect(Collectors.toList());
    }

    public ProductResponseDTO findById(Long id){

        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        Set<CategoryResponseDTO> categories = product.getCategories().stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toSet());
        return mapper.toResponse(product, categories);
    }

    public ProductResponseDTO insert(ProductRequestDTO request){
        Product product = repository.save(mapper.toEntity(request));
        Set<CategoryResponseDTO> categories = product.getCategories().stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toSet());

        return mapper.toResponse(product, categories);
    }

    public ProductResponseDTO update(Long id, ProductUpdateRequestDTO request){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }
        Product product = repository.getReferenceById(id);
        product.setName(request.name());
        product.setDescription(request.description());
        Set<CategoryResponseDTO> categories = product.getCategories().stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toSet());
        return mapper.toResponse(repository.save(product), categories);
    }

    public void delete(Long id){

        if(!repository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
