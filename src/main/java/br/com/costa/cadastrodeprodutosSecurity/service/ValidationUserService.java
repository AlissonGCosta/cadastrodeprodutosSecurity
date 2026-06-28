package br.com.costa.cadastrodeprodutosSecurity.service;

import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.UserReqeuestDto;
import br.com.costa.cadastrodeprodutosSecurity.exception.errocase.BusinessException;
import br.com.costa.cadastrodeprodutosSecurity.exception.errocase.ConflictException;
import br.com.costa.cadastrodeprodutosSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidationUserService {

    private final UserRepository userRepository;

    public void validateUser(UserReqeuestDto dto){

        if(userRepository.findByEmail(dto.email()).isPresent()){
            throw new ConflictException("email already exists");
        }

        // validate camps
        if(dto.password().length() < 8){
            throw new BusinessException("password too short");
        }

        if(dto.password().length() > 32){
            throw new BusinessException("password too long");
        }





    }
}
