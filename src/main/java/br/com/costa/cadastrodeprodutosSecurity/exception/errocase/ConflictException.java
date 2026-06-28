package br.com.costa.cadastrodeprodutosSecurity.exception.errocase;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
