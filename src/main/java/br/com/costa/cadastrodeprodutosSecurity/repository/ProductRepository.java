package br.com.costa.cadastrodeprodutosSecurity.repository;

import br.com.costa.cadastrodeprodutosSecurity.enitity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findByName(String name);
}
