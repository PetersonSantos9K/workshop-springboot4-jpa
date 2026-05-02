package com.petersonexercicio.course.repositories;

import com.petersonexercicio.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }
