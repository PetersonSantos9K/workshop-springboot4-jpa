package com.petersonexercicio.course.controllers;

import com.petersonexercicio.course.dto.request.create.CategoryRequestDTO;
import com.petersonexercicio.course.dto.response.CategoryResponseDTO;
import com.petersonexercicio.course.entities.Category;
import com.petersonexercicio.course.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
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
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> insert(@RequestBody CategoryRequestDTO request){
        CategoryResponseDTO category = service.insert(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.id())
                .toUri();
        return ResponseEntity.created(uri).body(category);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable Long id, @RequestBody CategoryResponseDTO request){
        return ResponseEntity.ok().body(service.update(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
