package com.petersonexercicio.course.services.mapper;

import com.petersonexercicio.course.dto.request.UserRequestDTO;
import com.petersonexercicio.course.dto.response.OrderResponseDTO;
import com.petersonexercicio.course.dto.response.UserResponseDTO;
import com.petersonexercicio.course.entities.User;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserMapper {


    public User toEntity(UserRequestDTO obj){
        return new User(
                null,
                obj.name(),
                obj.email(),
                obj.telephone(),
                obj.password()
        ) ;
    }

    public UserResponseDTO toResponse(User user, Set<OrderResponseDTO> orders){
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getTelephone(),
                orders
        );
    }


}
