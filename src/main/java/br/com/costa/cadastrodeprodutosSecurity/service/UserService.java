package br.com.costa.cadastrodeprodutosSecurity.service;

import br.com.costa.cadastrodeprodutosSecurity.config.PasswordConfig;
import br.com.costa.cadastrodeprodutosSecurity.enitity.entityenum.EntityStatus;
import br.com.costa.cadastrodeprodutosSecurity.enitity.UserEntity;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.UserReqeuestDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.UserResponseDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.mapper.UserMapper;
import br.com.costa.cadastrodeprodutosSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordConfig passwordConfig;
    private final ValidationUserService validationUserService;
    private final UserMapper userMapper;

    public void createUser(UserReqeuestDto userReqeuestDto) {

        // criando a validação do cadastro
        validationUserService.validateUser(userReqeuestDto);


        // salvando no MySqul
        userRepository.save(userMapper.toEntity(userReqeuestDto));

    }

    // metodo para listar todos os usuarios
    public List<UserResponseDto> listAllUsers() {

      return  userRepository.findAll().stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getStatus()

                )).toList();

    }
}
