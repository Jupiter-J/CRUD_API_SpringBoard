package com.example.crud.service;

import com.example.crud.entity.UserEntity;
import com.example.crud.model.UserDto;

import java.util.Collection;

public interface UserService {

    UserDto create(UserDto dto);
    Collection<UserDto> readAll();
    UserDto read(Long id);
    boolean update(UserDto dto, Long id);
    boolean delete(Long id);
}
