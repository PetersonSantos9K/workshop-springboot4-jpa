package com.petersonexercicio.course.repositories;

import com.petersonexercicio.course.entities.OrderItem;
import com.petersonexercicio.course.entities.User;
import com.petersonexercicio.course.entities.pk.OrderItemPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk> { }
