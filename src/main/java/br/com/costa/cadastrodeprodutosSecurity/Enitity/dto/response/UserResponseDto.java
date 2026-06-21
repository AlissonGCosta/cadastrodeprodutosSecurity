package br.com.costa.cadastrodeprodutosSecurity.Enitity.dto.response;

import br.com.costa.cadastrodeprodutosSecurity.Enitity.EntityEnum.EntityStatus;

import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String name,
        String email,
        EntityStatus status
) {
}
