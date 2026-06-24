package br.com.costa.cadastrodeprodutosSecurity.enitity.mapper;

import br.com.costa.cadastrodeprodutosSecurity.enitity.UserEntity;

import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.UserReqeuestDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.UserLoginResponseDto;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    // criando a resposta do controller/Post
    public UserLoginResponseDto userToUserLoginResponseDto(UserReqeuestDto dto) {
        return new UserLoginResponseDto(
                dto.email(),
                dto.name()

        );
    }

    // criando a entidade para salvar no repository
    public UserEntity toEntity(UserReqeuestDto dto) {
        return new UserEntity(
                dto.name(),
                dto.email(),
                dto.password()
        );
    }
}
