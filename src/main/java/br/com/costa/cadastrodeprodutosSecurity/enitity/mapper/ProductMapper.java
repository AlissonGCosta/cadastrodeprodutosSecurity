package br.com.costa.cadastrodeprodutosSecurity.enitity.mapper;

import br.com.costa.cadastrodeprodutosSecurity.enitity.ProductEntity;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.ProductRequestDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.ProductLoginResposneDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.productenum.ProductEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProductMapper {

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
}
