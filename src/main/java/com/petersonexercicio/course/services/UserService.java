package com.petersonexercicio.course.services;

import com.petersonexercicio.course.dto.request.UserRequestDTO;
import com.petersonexercicio.course.dto.response.UserResponseDTO;
import com.petersonexercicio.course.entities.User;
import com.petersonexercicio.course.repositories.UserRepository;
import com.petersonexercicio.course.services.exceptions.DatabaseException;
import com.petersonexercicio.course.services.exceptions.ResourceNotFoundException;
import com.petersonexercicio.course.services.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements CrudService<UserResponseDTO, UserRequestDTO>{

    private final UserRepository userRepository;
    private final UserMapper mapper;
    public UserService(final UserRepository repository, final UserMapper mapper){
        userRepository = repository;
        this.mapper = mapper;
    }

    public List<UserResponseDTO> findAll(){
      return userRepository.findAll().stream().map(mapper::toResponse).collect(java.util.stream.Collectors.toList());
    }

    public UserResponseDTO findById(Long id){
        Optional<User> obj = userRepository.findById(id);
        return mapper.toResponse(obj.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public UserResponseDTO insert(UserRequestDTO requestDTO) {

        User user = userRepository.save(mapper.toEntity(requestDTO));

        return mapper.toResponse(user);
    }

    public UserResponseDTO update(Long id, UserRequestDTO requestDTO){
        try{
            User entity = userRepository.getReferenceById(id);
            updateData(entity, mapper.toEntity(requestDTO));
            return mapper.toResponse(userRepository.save(entity));
        } catch (EntityNotFoundException e ){
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Long id){
        try{
            boolean exists = userRepository.existsById(id);
            if (!exists){
                throw new ResourceNotFoundException(id);
            }
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(User entity, User user) {
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setTelephone(user.getTelephone());
    }

}
