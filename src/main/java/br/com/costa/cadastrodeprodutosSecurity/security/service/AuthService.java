package br.com.costa.cadastrodeprodutosSecurity.security.service;

import br.com.costa.cadastrodeprodutosSecurity.enitity.UserEntity;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.UserLoginRequestDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.UserLoginAuthResponseDto;
import br.com.costa.cadastrodeprodutosSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import static br.com.costa.cadastrodeprodutosSecurity.utils.Utils.logger;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserLoginAuthResponseDto login( UserLoginRequestDto dto) {

        logger.info("Iniciate Login :: UserLoginRequestDto");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.email(),
                        dto.password()
                )
        );

        UserEntity userEntity = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        String token = jwtService.generateToken(userEntity);

        return new UserLoginAuthResponseDto(
                userEntity.getName(),
                userEntity.getEmail(),
                token

        );

    }


}
