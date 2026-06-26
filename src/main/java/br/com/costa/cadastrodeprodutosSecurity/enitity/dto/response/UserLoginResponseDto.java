package br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response;

import br.com.costa.cadastrodeprodutosSecurity.enitity.entityenum.EntityStatus;

import java.util.UUID;

public record UserLoginResponseDto(

        UUID id,
        String name,
        String email,
        String status


) {
}
