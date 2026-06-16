package com.petersonexercicio.course.services;
import com.petersonexercicio.course.dto.request.create.UserRequestDTO;
import com.petersonexercicio.course.dto.response.UserResponseDTO;
import com.petersonexercicio.course.entities.User;
import com.petersonexercicio.course.repositories.UserRepository;
import com.petersonexercicio.course.services.exceptions.ResourceAlreadyRegistered;
import com.petersonexercicio.course.services.exceptions.ResourceNotFoundException;
import com.petersonexercicio.course.services.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository repository;
    private final UserMapper mapper;

    public List<UserResponseDTO> findAll() {



        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponseDTO findById(Long id) {
        Optional<User> user = repository.findById(id);
        return mapper.toResponse(
                user.orElseThrow(
                        () -> new ResourceNotFoundException(id)
                )
        );
    }

    public UserResponseDTO insert(UserRequestDTO obj) {

        boolean exists = repository.existsByEmail(obj.email());
        if(exists){
            throw new ResourceAlreadyRegistered(obj.email());
        }
        User user = repository.save(mapper.toEntity(obj));
        return mapper.toResponse(user);
    }

    public UserResponseDTO update(Long id, UserRequestDTO obj) {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }
        User user = repository.getReferenceById(id);
        updateUser(user, mapper.toEntity(obj));
        return mapper.toResponse(repository.save(user));
    }

    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private void updateUser(User entity, User request){
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setTelephone(request.getTelephone());
    }



}
