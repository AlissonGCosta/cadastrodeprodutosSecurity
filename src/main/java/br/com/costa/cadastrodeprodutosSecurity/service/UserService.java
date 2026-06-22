package br.com.costa.cadastrodeprodutosSecurity.service;

import br.com.costa.cadastrodeprodutosSecurity.config.PasswordConfig;
import br.com.costa.cadastrodeprodutosSecurity.enitity.entityenum.EntityStatus;
import br.com.costa.cadastrodeprodutosSecurity.enitity.UserEntity;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.UserReqeuestDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.UserResponseDto;
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

    public void createUser(UserReqeuestDto userReqeuestDto) {

        if(userReqeuestDto.name().isEmpty() || userReqeuestDto.email().isEmpty() || userReqeuestDto.password().isEmpty()) {
            throw new RuntimeException("Campos não preenchidos");
        }


        UserEntity userEntity = new UserEntity();
                userEntity.setName(userReqeuestDto.name());
                userEntity.setEmail(userReqeuestDto.email());
                userEntity.setPassword(passwordConfig.passwordEncoder().encode(userReqeuestDto.password()));
                userEntity.setStatus(EntityStatus.USER);


        userRepository.save(userEntity);

    }

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
