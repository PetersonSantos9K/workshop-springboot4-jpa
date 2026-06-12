package com.petersonexercicio.course.services.mapper;

import com.petersonexercicio.course.dto.request.create.OrderRequestDTO;
import com.petersonexercicio.course.dto.response.OrderItemResponseDTO;
import com.petersonexercicio.course.dto.response.OrderResponseDTO;
import com.petersonexercicio.course.entities.Order;
import com.petersonexercicio.course.entities.OrderItem;
import com.petersonexercicio.course.entities.User;
import com.petersonexercicio.course.entities.enums.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OrderMapper {

    public Order toEntity(OrderRequestDTO orderRequestDTO, User user){

        return new Order(
                null,
                orderRequestDTO.moment(),
                OrderStatus.valueOf(orderRequestDTO.status()),
                user
        );
    }

    public OrderResponseDTO toResponse(Order order, Set<OrderItemResponseDTO> items){
        return new OrderResponseDTO(
                order.getId(),
                order.getMoment(),
                order.getOrderStatus(),
                order.getPayment(),
                items,
                order.getTotal()
        );
    }
}
