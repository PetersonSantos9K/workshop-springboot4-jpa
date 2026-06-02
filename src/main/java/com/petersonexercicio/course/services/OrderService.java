package com.petersonexercicio.course.services;

import com.petersonexercicio.course.entities.Order;
import com.petersonexercicio.course.repositories.OrderRepository;
import com.petersonexercicio.course.services.exceptions.ResourceNotFoundException;
import com.petersonexercicio.course.services.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements CrudService<Order>{

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        ValidationUtils.checkId(id);
        Optional<Order> obj = orderRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public Order insert(Order obj) {
        return orderRepository.save(obj);
    }

    @Override
    public Order update(Long id, Order obj) {
        ValidationUtils.checkId(id);



        return null;
    }

    @Override
    public void delete(Long id) {
    }

    private void updateOrder(Order entity, Order order){
    }
}
