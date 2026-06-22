package br.com.costa.cadastrodeprodutosSecurity.service;

import br.com.costa.cadastrodeprodutosSecurity.enitity.ProductEntity;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.ProductRequestDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.ProductResposneDto;
import br.com.costa.cadastrodeprodutosSecurity.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequestDto dto){

        if(dto.price().intValue() < 0){
            throw new RuntimeException("the value must be greater than zero");
        }

        if(dto.quantity() < 0){
            throw new RuntimeException("the quantity must be greater than zero");
        }

        ProductEntity product = new ProductEntity();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setQuantity(dto.quantity());

        productRepository.save(product);
    }

    public List<ProductResposneDto> findAll(){

       return productRepository.findAll().stream()
               .map(product -> new ProductResposneDto(
                       product.getId(),
                       product.getName(),
                       product.getDescription(),
                       product.getPrice(),
                       product.getQuantity()
               )).toList();
    }
}
