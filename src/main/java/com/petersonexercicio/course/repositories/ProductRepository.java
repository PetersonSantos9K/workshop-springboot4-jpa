package com.petersonexercicio.course.repositories;

import com.petersonexercicio.course.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
