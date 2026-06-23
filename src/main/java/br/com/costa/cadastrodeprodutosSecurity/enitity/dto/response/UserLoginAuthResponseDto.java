package br.com.costa.cadastrodeprodutosSecurity.enitity.dto.response;

public record UserLoginAuthResponseDto(
        String name,
        String email,
        String token

) {
}
