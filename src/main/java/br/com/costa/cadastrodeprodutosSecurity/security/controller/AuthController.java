package br.com.costa.cadastrodeprodutosSecurity.security.controller;

import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.UserLoginRequestDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.UserLoginResponseDto;
import br.com.costa.cadastrodeprodutosSecurity.security.service.AuthService;
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
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        return  authService.login(userLoginRequestDto);
    }
}
