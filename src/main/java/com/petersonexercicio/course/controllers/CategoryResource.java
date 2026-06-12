package com.petersonexercicio.course.controllers;

import com.petersonexercicio.course.dto.request.create.CategoryRequestDTO;
import com.petersonexercicio.course.dto.response.CategoryResponseDTO;
import com.petersonexercicio.course.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categories")
public class CategoryResource {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<Set<CategoryResponseDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable @Valid Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> insert(@RequestBody @Valid CategoryRequestDTO request){
        CategoryResponseDTO category = service.insert(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.id())
                .toUri();
        return ResponseEntity.created(uri).body(category);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable @Valid Long id, @RequestBody @Valid CategoryResponseDTO request){
        return ResponseEntity.ok().body(service.update(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
