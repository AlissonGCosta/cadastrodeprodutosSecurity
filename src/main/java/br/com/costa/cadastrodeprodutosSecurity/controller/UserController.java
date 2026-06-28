package br.com.costa.cadastrodeprodutosSecurity.controller;


import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.UserReqeuestDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.UserLoginResponseDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.UserResponseDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.mapper.UserMapper;
import br.com.costa.cadastrodeprodutosSecurity.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("v1/users")
@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserLoginResponseDto createUser(@RequestBody @Valid UserReqeuestDto dto) {
        userService.createUser(dto);

        return userMapper.userToUserLoginResponseDto(dto);

        
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> getAllUsers() {

       return userService.listAllUsers();
    }
}
