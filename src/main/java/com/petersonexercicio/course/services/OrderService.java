package com.petersonexercicio.course.services;
import com.petersonexercicio.course.dto.request.create.OrderRequestDTO;
import com.petersonexercicio.course.dto.request.update.OrderUpdateRequestDTO;
import com.petersonexercicio.course.dto.response.OrderResponseDTO;
import com.petersonexercicio.course.entities.Order;
import com.petersonexercicio.course.entities.User;
import com.petersonexercicio.course.entities.enums.OrderStatus;
import com.petersonexercicio.course.repositories.OrderRepository;
import com.petersonexercicio.course.repositories.UserRepository;
import com.petersonexercicio.course.services.exceptions.ResourceNotFoundException;
import com.petersonexercicio.course.services.mapper.OrderMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderService{

    private final UserRepository userRepository;
    private final OrderRepository repository;
    private final OrderMapper mapper;

    public OrderService(final UserRepository user, final OrderRepository repository, final OrderMapper mapper){
        this.userRepository = user;
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<OrderResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public OrderResponseDTO findById(Long id) {
        Optional<Order> order = repository.findById(id);
        return mapper.toResponse(
                order.orElseThrow(
                        () -> new ResourceNotFoundException(id)
                )
        );
    }

    public OrderResponseDTO insert(OrderRequestDTO obj) {
        User user = userRepository.findById(obj.userId())
                .orElseThrow(() -> new ResourceNotFoundException(obj.userId()));
        Order order = repository.save(mapper.toEntity(obj, user));
        return mapper.toResponse(order);
    }

    public OrderResponseDTO update(Long id, OrderUpdateRequestDTO obj) {
        try{
            Order order = repository.getReferenceById(id);
            order.setOrderStatus(OrderStatus.valueOf(obj.status()));
            return mapper.toResponse(repository.save(order));
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
