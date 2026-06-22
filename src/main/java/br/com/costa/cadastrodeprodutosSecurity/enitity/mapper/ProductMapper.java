package br.com.costa.cadastrodeprodutosSecurity.enitity.mapper;

import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.ProductRequestDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.ProductLoginResposneDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductLoginResposneDto toProductLoginResponse(ProductRequestDto dto) {
        return new ProductLoginResposneDto(
                dto.name(),
                dto.description(),
                dto.price(),
                dto.quantity()
        );
    }
}
