package com.petersonexercicio.course.repositories;

import com.petersonexercicio.course.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> { }
