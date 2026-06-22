package br.com.costa.cadastrodeprodutosSecurity.repository;

import br.com.costa.cadastrodeprodutosSecurity.enitity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
