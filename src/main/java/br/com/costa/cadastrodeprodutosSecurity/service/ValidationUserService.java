package br.com.costa.cadastrodeprodutosSecurity.service;

import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.UserReqeuestDto;
import br.com.costa.cadastrodeprodutosSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidationUserService {

    private final UserRepository userRepository;

    public void validateUser(UserReqeuestDto dto){

        if(userRepository.findByEmail(dto.email()).isPresent()){
            throw new RuntimeException("email already exists");
        }

        if(dto.password().length() < 8){
            throw new RuntimeException("password too short");
        }

        if(dto.password().length() > 32){
            throw new RuntimeException("password too long");
        }

        if(dto.email().isEmpty()){
            throw new RuntimeException("email is empty");
        }

        if(dto.name().isEmpty()){
            throw new RuntimeException("name is empty");
        }



    }
}
