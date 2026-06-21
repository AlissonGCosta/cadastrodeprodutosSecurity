package br.com.costa.cadastrodeprodutosSecurity.repository;

import br.com.costa.cadastrodeprodutosSecurity.Enitity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}
