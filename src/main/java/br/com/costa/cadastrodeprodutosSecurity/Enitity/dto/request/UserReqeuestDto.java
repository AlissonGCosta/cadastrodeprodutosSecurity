package br.com.costa.cadastrodeprodutosSecurity.Enitity.dto.request;

import br.com.costa.cadastrodeprodutosSecurity.Enitity.EntityEnum.EntityStatus;

public record UserReqeuestDto(

         String name,
         String email,
         String password

) {
}
