package com.petersonexercicio.course.controllers;

import com.petersonexercicio.course.dto.request.create.ProductCategoryRequestDTO;
import com.petersonexercicio.course.dto.request.create.ProductRequestDTO;
import com.petersonexercicio.course.dto.request.update.ProductUpdateRequestDTO;
import com.petersonexercicio.course.dto.response.ProductResponseDTO;
import com.petersonexercicio.course.services.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
@Validated
public class ProductResource {

    private final ProductService productService;
    private final String invalidId = "Id must be a positive number";

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll(){
        return ResponseEntity.ok().body(productService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable @Positive(message = invalidId) Long id){
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> insert(@RequestBody @Valid ProductRequestDTO request){

        ProductResponseDTO product = productService.insert(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.id())
                .toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> insertCategory(@PathVariable @Positive(message = invalidId) Long id, @RequestBody @Valid ProductCategoryRequestDTO request){
        ProductResponseDTO product = productService.insertCategory(id, request);
        return ResponseEntity.ok().body(product);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable @Positive(message = invalidId) Long id, @RequestBody @Valid ProductUpdateRequestDTO request){
        return ResponseEntity.ok().body(productService.update(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive(message = invalidId) Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
