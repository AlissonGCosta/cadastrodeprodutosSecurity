package br.com.costa.cadastrodeprodutosSecurity.enitity.mapper;

import br.com.costa.cadastrodeprodutosSecurity.enitity.ProductEntity;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.ProductRequestDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.ProductLoginResposneDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.ProductResposneDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.productenum.ProductEnum;
import br.com.costa.cadastrodeprodutosSecurity.exception.errocase.RessourceNotFoundException;
import br.com.costa.cadastrodeprodutosSecurity.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ProductRepository productRepository;



    // resposta do controler/post
    public ProductLoginResposneDto toProductLoginResponse(ProductRequestDto dto) {
        return new ProductLoginResposneDto(
                dto.name(),
                dto.description(),
                dto.price(),
                dto.category(),
                dto.quantity()
        );
    }

    // criando ha entidade produto para salvar no repository
    public ProductEntity toProductEntity(ProductRequestDto dto) {
     return new ProductEntity(
                dto.name(),
                dto.description(),
                dto.price(),
                dto.category(),
                dto.quantity()
        );
    }

    public ProductResposneDto toProductResponse(ProductEntity dto) {
        return new ProductResposneDto(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getQuantity(),
                dto.getCategory()
        );
    }

    public ProductEntity putToProductEntity(UUID id, ProductRequestDto dto) {

        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("id not found"));


        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setCategory(dto.category());
        product.setQuantity(dto.quantity());


        return product;

    }

}
