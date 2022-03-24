package com.example.crud.service;

import com.example.crud.entity.UserEntity;
import com.example.crud.model.UserDto;
import com.example.crud.repository.UserRepository;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class JpaUserService implements UserService{
    private final Logger logger = LoggerFactory.getLogger(JpaUserService.class);
    private final UserRepository userRepository;

    public JpaUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDto create(UserDto dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(dto.getPassword());
        userEntity = this.userRepository.save(userEntity);
        return new UserDto(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword()
        );
    }

    @Override
    public Collection<UserDto> readAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        this.userRepository.findAll().forEach(
                userEntity -> userDtoList.add(new UserDto(
                        userEntity.getId(),
                        userEntity.getUsername(),
                        userEntity.getPassword()
                )));

        return userDtoList;
    }

    @Override
    public UserDto read(Long id) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        UserEntity userEntity = userEntityOptional.get();
        return new UserDto(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword()
        );
    }

    @Override
    public boolean update(UserDto dto, Long id) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        UserEntity userEntity = userEntityOptional.get();
        userEntity.setPassword(
                dto.getPassword() == null? userEntity.getPassword() : dto.getPassword()
        );
        return true;
    }

    @Override
    public boolean delete(Long id) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        if (userEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        UserEntity userEntity = userEntityOptional.get();
        this.userRepository.delete(userEntity);
        return true;
    }
}
