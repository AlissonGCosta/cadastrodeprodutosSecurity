package br.com.costa.cadastrodeprodutosSecurity.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record Error(
         String field,
         String message
) {


}
