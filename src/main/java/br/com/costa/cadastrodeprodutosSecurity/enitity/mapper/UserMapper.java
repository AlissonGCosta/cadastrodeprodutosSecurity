package br.com.costa.cadastrodeprodutosSecurity.enitity.mapper;

import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.UserReqeuestDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.UserLoginResponseDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserLoginResponseDto userToUserLoginResponseDto(UserReqeuestDto dto) {
        return new UserLoginResponseDto(
                dto.email(),
                dto.name()
        );
    }
}
