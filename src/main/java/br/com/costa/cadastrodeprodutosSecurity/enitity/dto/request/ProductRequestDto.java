package br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
@Validated
public record ProductRequestDto(

         @NotBlank(message = "name is empty")
         String name,

         @NotBlank(message = "description is empty")
         String description,

         @NotNull(message = "price is empty")
         BigDecimal price,

         @NotBlank(message = "category is empty")
         String category,

         @NotNull(message = "quantity is empty")
         @Positive(message = "negative quantity is not possible")
         int quantity
) {
}
