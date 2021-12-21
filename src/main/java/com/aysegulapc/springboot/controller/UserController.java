package com.aysegulapc.springboot.controller;

import com.aysegulapc.springboot.converter.UserConverter;
import com.aysegulapc.springboot.dto.UserDto;
import com.aysegulapc.springboot.entity.User;
import com.aysegulapc.springboot.entityService.UserEntityService;
import com.aysegulapc.springboot.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserEntityService userEntityService;

    @GetMapping("")
    public List<UserDto> findAllUsers() {
        List<User> userList = userEntityService.findAll();
        List<UserDto> userDtoList = UserConverter.INSTANCE.convertUserListToUserDtoList(userList);
        return userDtoList;
    }

    @GetMapping("/names/{userName}")
    public UserDto findUserByUserName(@PathVariable String userName) {
        User user = userEntityService.findAllByUserName(userName);
        UserDto userDto = UserConverter.INSTANCE.convertUserToUserDto(user);
        return userDto;
    }

    @GetMapping("/phones/{phoneNumber}")
    public UserDto findUserByPhoneNumber(@PathVariable Long phoneNumber) {
        User user = userEntityService.findAllByPhone(phoneNumber);
        UserDto userDto = UserConverter.INSTANCE.convertUserToUserDto(user);
        return userDto;
    }

    @PostMapping("")
    public ResponseEntity<Object> save(@RequestBody UserDto userDto) {
        User user = UserConverter.INSTANCE.convertUserDtoToUser(userDto);
        user = userEntityService.save(user);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{userName}/{phone}")
    public void deleteUser(@PathVariable String userName, @PathVariable Long phone) {
        User user = userEntityService.findAllByPhone(phone);
        if(userEntityService.findAllByPhone(phone) == null ||
                userEntityService.findAllByUserName(userName) == null ||
                !Objects.equals(user.getId(), userEntityService.findAllByUserName(userName).getId())
        ) {
            throw new UserNotFoundException(userName + " kullanıcı adı ile " + phone + " telefon bilgileri uyuşmamaktadır");
        }
        userEntityService.deleteUserByUserNameAndPhone(userName, phone);

    }

    @PutMapping("")
    public UserDto update(@RequestBody UserDto userDto) {
        User user = UserConverter.INSTANCE.convertUserDtoToUser(userDto);
        user = userEntityService.save(user);
        UserDto updatedUserDto = UserConverter.INSTANCE.convertUserToUserDto(user);
        return updatedUserDto;
    }

}
