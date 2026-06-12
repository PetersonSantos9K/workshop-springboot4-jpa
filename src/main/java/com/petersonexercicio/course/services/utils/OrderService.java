package com.petersonexercicio.course.services.utils;

import com.petersonexercicio.course.dto.request.create.OrderRequestDTO;
import com.petersonexercicio.course.dto.response.OrderResponseDTO;
import com.petersonexercicio.course.entities.Order;
import com.petersonexercicio.course.entities.Product;
import com.petersonexercicio.course.entities.User;
import com.petersonexercicio.course.entities.enums.OrderStatus;
import com.petersonexercicio.course.repositories.OrderRepository;
import com.petersonexercicio.course.repositories.ProductRepository;
import com.petersonexercicio.course.repositories.UserRepository;
import com.petersonexercicio.course.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;



    public OrderResponseDTO insert(OrderRequestDTO requestDTO){

        if(!userRepository.existsById(requestDTO.userId())){
            throw new ResourceNotFoundException(requestDTO.userId());
        }

        User userEntity = userRepository.getReferenceById(requestDTO.userId());

        List<Product> productsEntity = productRepository.findAllById(requestDTO.productIds());
        if(productsEntity.size() != requestDTO.productIds().size()){
            throw new ResourceNotFoundException("One or more products not found");
        }

        Order order = new Order(
                null,
                requestDTO.moment(),
                OrderStatus.valueOf(requestDTO.status()),
                userEntity
        );



        order.getItems().add(null);



        return null;
    }




}
