package br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResposneDto(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        int quantity,
        String category
) {
}
