package br.com.costa.cadastrodeprodutosSecurity.security.controller;

import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.UserLoginRequestDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.UserLoginAuthResponseDto;

import br.com.costa.cadastrodeprodutosSecurity.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserLoginAuthResponseDto login(@RequestBody @Valid UserLoginRequestDto userLoginRequestDto) {
        return  authService.login(userLoginRequestDto);
    }
}
