package com.petersonexercicio.course.services;

import com.petersonexercicio.course.dto.request.create.OrderItemRequestDTO;
import com.petersonexercicio.course.dto.request.create.OrderRequestDTO;
import com.petersonexercicio.course.dto.request.update.OrderUpdateRequestDTO;
import com.petersonexercicio.course.dto.response.OrderItemResponseDTO;
import com.petersonexercicio.course.dto.response.OrderResponseDTO;
import com.petersonexercicio.course.entities.Order;
import com.petersonexercicio.course.entities.OrderItem;
import com.petersonexercicio.course.entities.Product;
import com.petersonexercicio.course.entities.User;
import com.petersonexercicio.course.entities.enums.OrderStatus;
import com.petersonexercicio.course.repositories.OrderRepository;
import com.petersonexercicio.course.repositories.ProductRepository;
import com.petersonexercicio.course.repositories.UserRepository;
import com.petersonexercicio.course.services.exceptions.ResourceNotFoundException;
import com.petersonexercicio.course.services.mapper.OrderItemMapper;
import com.petersonexercicio.course.services.mapper.OrderMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderItemMapper orderItemMapper;

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderResponseDTO findById(Long id){
        Order order = findByIdCheck(id);
        return mapper.toResponse(order, mapperItems(order.getItems()));
    }

    public OrderResponseDTO insert(OrderRequestDTO requestDTO){

        if(!userRepository.existsById(requestDTO.userId())){
            throw new ResourceNotFoundException(requestDTO.userId());
        }
        User user = userRepository.getReferenceById(requestDTO.userId());
        Order order = repository.save(mapper.toEntity(requestDTO, user));
        order.getItems().addAll(buildItems(order, requestDTO.items()));

        repository.save(order);
        return mapper.toResponse(
                order,
                order.getItems().stream()
                        .map(orderItemMapper::toResponse)
                        .collect(Collectors.toSet())
        );
    }

    public void delete(Long id){
        Order order = getReferenceCheck(id);
        repository.delete(order);
    }

    public OrderResponseDTO updateStatus(Long id, OrderUpdateRequestDTO requestDTO){
        Order order = getReferenceCheck(id);
        order.setOrderStatus(OrderStatus.valueOf(requestDTO.status()));
        repository.save(order);
        return mapper.toResponse(order, mapperItems(order.getItems()));
    }



    private Map<Long, Product> fetchProducts(List<Long> ids) {
        List<Product> products = productRepository.findAllById(ids);

        if(products.size() < ids.size()){
            throw new ResourceNotFoundException("One or more products not found");
        }

        return products.stream()
                .collect(Collectors.toMap(Product::getId, x->x));
    }

    private Set<OrderItem> buildItems(Order order, List<OrderItemRequestDTO> DTOs){

        Map<Long, Product> productMap = fetchProducts(DTOs.stream().map(OrderItemRequestDTO::productId).toList());

        Set<OrderItem> items = new HashSet<>();

        for(OrderItemRequestDTO item : DTOs){
            Product product = productMap.get(item.productId());
            items.add(orderItemMapper.toEntity(order, product, item.quantity(), product.getPrice()));
        }

        return items;
    }

    private Order findByIdCheck(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    private Order getReferenceCheck(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }

        return repository.getReferenceById(id);
    }


    private Set<OrderItemResponseDTO> mapperItems(Set<OrderItem> items){
        return items.stream().map(orderItemMapper::toResponse).collect(Collectors.toSet());
    }

}
