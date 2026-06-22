package br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response;

import br.com.costa.cadastrodeprodutosSecurity.enitity.entityenum.EntityStatus;

import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String name,
        String email,
        EntityStatus status
) {
}
