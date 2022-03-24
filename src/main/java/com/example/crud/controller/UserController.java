package com.example.crud.controller;

import com.example.crud.entity.UserEntity;
import com.example.crud.model.UserDto;
import com.example.crud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserDto> createUser
            (@RequestBody UserDto userDto){
        UserDto result = this.userService.create(userDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Collection<UserDto>> readUserAll(){
        Collection<UserDto> userDtoList = userService.readAll();
        if (userDtoList == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> readUser(@PathVariable ("id") Long id){
        return ResponseEntity.ok(this.userService.read(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser (@RequestBody UserDto dto,
                                          @PathVariable Long id){
        this.userService.update(dto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        this.userService.delete(id);
        return ResponseEntity.ok().build();
    }


}
