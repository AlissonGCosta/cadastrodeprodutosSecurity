package br.com.costa.cadastrodeprodutosSecurity.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse {

    private List<Error> errors;

    public ErrorResponse(List<Error> errors) {
        this.errors = new ArrayList<>();
    }

    public void addError(Error error) {
        this.errors.add(error);
    }
}
