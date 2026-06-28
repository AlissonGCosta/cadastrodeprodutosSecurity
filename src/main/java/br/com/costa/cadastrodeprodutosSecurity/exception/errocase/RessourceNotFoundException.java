package br.com.costa.cadastrodeprodutosSecurity.exception.errocase;

public class RessourceNotFoundException extends RuntimeException {
    public RessourceNotFoundException(String message) {
        super(message);
    }
}
