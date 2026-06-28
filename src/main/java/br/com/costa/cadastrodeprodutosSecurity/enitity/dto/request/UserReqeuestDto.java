package br.com.costa.cadastrodeprodutosSecurity.enitity.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record UserReqeuestDto(

         @NotBlank(message = "name is empty")
         String name,
         @NotBlank(message = "email is empty")
         String email,

         @NotBlank(message = "Password is empty")
         String password

) {
}
