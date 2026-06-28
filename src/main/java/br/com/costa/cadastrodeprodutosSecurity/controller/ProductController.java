package br.com.costa.cadastrodeprodutosSecurity.controller;

import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request.ProductRequestDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.ProductLoginResposneDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response.ProductResposneDto;
import br.com.costa.cadastrodeprodutosSecurity.enitity.mapper.ProductMapper;
import br.com.costa.cadastrodeprodutosSecurity.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/product")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductLoginResposneDto createProduct(@RequestBody @Valid ProductRequestDto dto) {
        productService.createProduct(dto);
        return productMapper.toProductLoginResponse(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResposneDto> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResposneDto getProductById( @PathVariable  UUID id) {
       return productService.findById(id);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductLoginResposneDto updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductRequestDto dto) {

        productService.putProduct(id, dto);

        return productMapper.toProductLoginResponse(dto);
    }
}
