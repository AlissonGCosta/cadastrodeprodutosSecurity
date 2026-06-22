package br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request;

import org.springframework.validation.annotation.Validated;

@Validated
public record UserReqeuestDto(

         String name,
         String email,
         String password

) {
}
