package br.com.costa.cadastrodeprodutosSecurity.security.service;

import br.com.costa.cadastrodeprodutosSecurity.enitity.UserEntity;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.UserLoginRequestDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.UserLoginResponseDto;
import br.com.costa.cadastrodeprodutosSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserLoginResponseDto login( UserLoginRequestDto dto) {
        UserEntity userEntity = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));


        boolean passwordMatches = passwordEncoder.matches(
                dto.senha(),
                userEntity.getPassword()
        );
        if (!passwordMatches) {
            throw new RuntimeException("senha invalida");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.email(),
                        dto.senha()
                )
        );

        return new UserLoginResponseDto(
                userEntity.getName(),
                userEntity.getEmail()
        );
    }


}
