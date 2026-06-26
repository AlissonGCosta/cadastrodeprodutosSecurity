package br.com.costa.cadastrodeprodutosSecurity.service;

import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.ProductRequestDto;
import br.com.costa.cadastrodeprodutosSecurity.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductValidationService {

    private final ProductRepository productRepository;


    public void validate(ProductRequestDto dto) {

        //validate database
        if(productRepository.findByName(dto.name()).isPresent()) {
            throw new RuntimeException("Product already exists");
        }

        // validate of camp
        if(dto.name().isEmpty()) {
            throw new RuntimeException("name is required");
        }

        if(dto.description().length() < 10){
            throw new RuntimeException("Description is too short");
        }

        if(dto.price().doubleValue() < 0){
            throw new RuntimeException("Price negative cant possible");
        }

        if(dto.price().doubleValue() == 0){
            throw new RuntimeException("Price cant be zero");
        }

        if(dto.quantity() < 0){
            throw new RuntimeException("Quantity negative cant possible");
        }

        if (dto.category().isEmpty()) {
            throw new RuntimeException("Category is required");
        }




    }
}
