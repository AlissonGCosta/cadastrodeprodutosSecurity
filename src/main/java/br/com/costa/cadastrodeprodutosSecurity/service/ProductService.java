package br.com.costa.cadastrodeprodutosSecurity.service;

import br.com.costa.cadastrodeprodutosSecurity.enitity.ProductEntity;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.ProductRequestDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.ProductResposneDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.mapper.ProductMapper;
import br.com.costa.cadastrodeprodutosSecurity.enitity.productenum.ProductEnum;
import br.com.costa.cadastrodeprodutosSecurity.exception.errocase.RessourceNotFoundException;
import br.com.costa.cadastrodeprodutosSecurity.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static br.com.costa.cadastrodeprodutosSecurity.utils.Utils.logger;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductValidationService productValidationService;
    private final ProductMapper productMapper;

    public void createProduct(ProductRequestDto dto){

        // validando o produto
        productValidationService.validate(dto);

        logger.info("dados recebidos: {}", dto );
        // salvando o produto
        productRepository.save(productMapper.toProductEntity(dto));
    }

    public List<ProductResposneDto> findAll(){

       return productRepository.findAll().stream()
               .map(product -> new ProductResposneDto(
                       product.getId(),
                       product.getName(),
                       product.getDescription(),
                       product.getPrice(),
                       product.getQuantity(),
                       product.getCategory()
               )).toList();
    }

    public ProductResposneDto findById(UUID id){

        var product = productRepository.findById(id).
                orElseThrow(() -> new RessourceNotFoundException("product not found"));


        return  productMapper.toProductResponse(product);

    }
}
