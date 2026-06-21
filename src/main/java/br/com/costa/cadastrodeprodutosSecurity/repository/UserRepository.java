package br.com.costa.cadastrodeprodutosSecurity.repository;

import br.com.costa.cadastrodeprodutosSecurity.Enitity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
