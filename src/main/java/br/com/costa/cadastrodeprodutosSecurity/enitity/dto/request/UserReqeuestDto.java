package br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record UserReqeuestDto(

         @NotBlank
         String name,
         @NotBlank
         String email,
         @NotBlank
         String password

) {
}
