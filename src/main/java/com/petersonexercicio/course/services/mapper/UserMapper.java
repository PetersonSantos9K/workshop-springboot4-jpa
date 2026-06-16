package com.petersonexercicio.course.services.mapper;

import com.petersonexercicio.course.dto.request.create.OrderRequestDTO;
import com.petersonexercicio.course.dto.request.create.UserRequestDTO;
import com.petersonexercicio.course.dto.response.OrderResponseDTO;
import com.petersonexercicio.course.dto.response.UserResponseDTO;
import com.petersonexercicio.course.entities.Order;
import com.petersonexercicio.course.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserMapper {

    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    public User toEntity(UserRequestDTO userRequest){
        return new User(
                null,
                userRequest.name(),
                userRequest.email(),
                userRequest.telephone(),
                userRequest.password()
        );
    }

    public UserResponseDTO toResponse(User user){

        Set<OrderResponseDTO> orders = user.getOrders()
                .stream()
                .map(order -> orderMapper.toResponse(order,
                        order.getItems().stream()
                                .map(orderItemMapper::toResponse)
                                .collect(Collectors.toSet())
                ))
                .collect(Collectors.toSet());

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getTelephone(),
                orders
        );
    }
}
