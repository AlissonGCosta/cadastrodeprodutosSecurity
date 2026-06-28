package br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
@Validated
public record ProductRequestDto(
        @NotBlank
         String name,
         @NotBlank
         String description,

         @NotNull
         @Positive
         BigDecimal price,

         @NotBlank
         String category,

         @NotNull
         @Positive
         int quantity
) {
}
