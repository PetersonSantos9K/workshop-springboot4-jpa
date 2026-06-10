package com.petersonexercicio.course.controllers;

import com.petersonexercicio.course.dto.request.create.ProductRequestDTO;
import com.petersonexercicio.course.dto.request.update.ProductUpdateRequestDTO;
import com.petersonexercicio.course.dto.response.ProductResponseDTO;
import com.petersonexercicio.course.dto.response.UserResponseDTO;
import com.petersonexercicio.course.entities.Product;
import com.petersonexercicio.course.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductResource {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll(){
        return ResponseEntity.ok().body(productService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> insert(@RequestBody ProductRequestDTO request){

        ProductResponseDTO product = productService.insert(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.id())
                .toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody ProductUpdateRequestDTO request){
        return ResponseEntity.ok().body(productService.update(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
