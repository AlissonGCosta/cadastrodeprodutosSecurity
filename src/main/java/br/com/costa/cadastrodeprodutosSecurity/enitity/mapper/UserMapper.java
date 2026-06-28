package br.com.costa.cadastrodeprodutosSecurity.enitity.mapper;

import br.com.costa.cadastrodeprodutosSecurity.config.PasswordConfig;
import br.com.costa.cadastrodeprodutosSecurity.enitity.UserEntity;

import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.UserReqeuestDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.UserLoginResponseDto;

import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.UserResponseDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.entityenum.EntityStatus;
import br.com.costa.cadastrodeprodutosSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordConfig passwordConfig;
    private final UserRepository userRepository;


    // criando a resposta do controller/Post
    public UserLoginResponseDto userToUserLoginResponseDto(UserReqeuestDto dto ) {

        UUID getID = userRepository.findByEmail(dto.email()).get().getId();
        String status = userRepository.findByEmail(dto.email()).get().getRole().name();

        return new UserLoginResponseDto(

                getID,
                dto.name(),
                dto.email(),
                status



        );
    }

    // criando a entidade para salvar no repository
    public UserEntity toEntity(UserReqeuestDto dto) {
        return new UserEntity(
                dto.name(),
                dto.email(),
                passwordConfig.passwordEncoder().encode(dto.password())

        );
    }


}
