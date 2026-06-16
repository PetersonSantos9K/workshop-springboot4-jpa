package com.petersonexercicio.course.controllers;


import com.petersonexercicio.course.dto.request.create.OrderRequestDTO;
import com.petersonexercicio.course.dto.request.update.OrderUpdateRequestDTO;
import com.petersonexercicio.course.dto.response.OrderResponseDTO;
import com.petersonexercicio.course.services.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
@Validated
public class OrderController {

    private final OrderService service;
    private final String invalidId = "Id must be a positive number";

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable @Positive(message = invalidId) Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> insert(@RequestBody @Valid OrderRequestDTO requestDTO){
        OrderResponseDTO responseDTO = service.insert(requestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.id())
                .toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive(message = invalidId) Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDTO> orderUpdateStatus(@PathVariable @Positive(message = invalidId) Long id, @RequestBody @Valid OrderUpdateRequestDTO requestDTO){
        return ResponseEntity.ok().body(service.updateStatus(id, requestDTO));
    }


}
