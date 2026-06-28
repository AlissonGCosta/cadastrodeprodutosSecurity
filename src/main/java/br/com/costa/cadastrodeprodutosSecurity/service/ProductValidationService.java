package br.com.costa.cadastrodeprodutosSecurity.service;

import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.ProductRequestDto;
import br.com.costa.cadastrodeprodutosSecurity.exception.errocase.BusinessException;
import br.com.costa.cadastrodeprodutosSecurity.exception.errocase.ConflictException;
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
            throw new ConflictException("Product already exists");
        }

        // validate of camp


        if(dto.description().length() < 10){
            throw new BusinessException("Description is too short");
        }


        if(dto.price().doubleValue() == 0){
            throw new BusinessException("Price cant be zero");
        }
        if(dto.price().doubleValue() < 0){
            throw new BusinessException("Price cant be minor zero");
        }







    }
}
