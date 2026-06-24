package br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductLoginResposneDto(

        String name,
        String description,
        BigDecimal price,
        String category,
        int quantity
) {
}
