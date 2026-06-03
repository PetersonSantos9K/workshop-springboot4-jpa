package com.petersonexercicio.course.services.mapper;

import com.petersonexercicio.course.dto.request.create.UserRequestDTO;
import com.petersonexercicio.course.dto.response.UserResponseDTO;
import com.petersonexercicio.course.entities.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class UserMapper {

    private final OrderMapper orderMapper;
    public UserMapper(final OrderMapper orderMapper){
        this.orderMapper = orderMapper;
    }

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
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getTelephone(),
                user.getOrders().stream().map(orderMapper::toResponse).collect(Collectors.toSet())
        );
    }
}
