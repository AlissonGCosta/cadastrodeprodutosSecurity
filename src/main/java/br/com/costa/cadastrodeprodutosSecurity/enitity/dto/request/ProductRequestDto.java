package br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
@Validated
public record ProductRequestDto(
         String name,
         String description,
         BigDecimal price,
         String category,
         int quantity
) {
}
