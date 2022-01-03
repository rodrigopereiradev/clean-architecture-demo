package br.com.rodrigo.cleanarchitecturedemo.domain.exceptions;

public class ProductException extends RuntimeException {

    public ProductException(String message) {
        super(message);
    }
}
