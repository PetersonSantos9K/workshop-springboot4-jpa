package com.petersonexercicio.course.services;

import com.petersonexercicio.course.dto.request.ProductRequestDTO;
import com.petersonexercicio.course.dto.response.ProductResponseDTO;
import com.petersonexercicio.course.entities.Product;
import com.petersonexercicio.course.repositories.ProductRepository;
import com.petersonexercicio.course.services.exceptions.DatabaseException;
import com.petersonexercicio.course.services.exceptions.ResourceNotFoundException;
import com.petersonexercicio.course.services.mapper.ProductMapper;
import com.petersonexercicio.course.services.utils.ValidationUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements CrudService<ProductResponseDTO, ProductRequestDTO>{

    private final ProductRepository productRepository;
    private final ProductMapper mapper;


    public ProductService(final ProductRepository productRepository, final ProductMapper mapper){
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponseDTO findById(Long id) {
        ValidationUtils.checkId(id);
        Optional<Product> obj = productRepository.findById(id);

        return mapper.toResponse(obj.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Override
    public ProductResponseDTO insert(ProductRequestDTO obj) {
        Product product = productRepository.save(mapper.toEntity(obj));
        return mapper.toResponse(product);
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO obj) {
        try{
            boolean exists = productRepository.existsById(id);
            if(!exists){
                throw new ResourceNotFoundException(id);
            }
            Product entity = productRepository.getReferenceById(id);
            updateProduct(entity, mapper.toEntity(obj));

            return mapper.toResponse(productRepository.save(entity));
        } catch (EntityNotFoundException e ){
            throw new ResourceNotFoundException(id);
        }
    }

    @Override
    public void delete(Long id) {
        try{
            boolean exists = productRepository.existsById(id);
            if (!exists){
                throw new ResourceNotFoundException(id);
            }
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public


    private void updateProduct(Product entity, Product product){
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setImgUrl(product.getImgUrl());
    }
}
