package com.petersonexercicio.course.services.mapper;

import com.petersonexercicio.course.dto.response.OrderItemResponseDTO;
import com.petersonexercicio.course.dto.response.ProductResponseDTO;
import com.petersonexercicio.course.entities.Order;
import com.petersonexercicio.course.entities.OrderItem;
import com.petersonexercicio.course.entities.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class OrderItemMapper {

    public OrderItem toEntity(Order order, Product product, Integer quantity, BigDecimal price){
        return new OrderItem(order, product, quantity, price);
    }

    public OrderItemResponseDTO toResponse(OrderItem orderItem){
        return new OrderItemResponseDTO(
                orderItem.getProduct().getId(),
                orderItem.getQuantity(),
                orderItem.getPrice(),
                orderItem.getSubTotal()
        );
    }

}
