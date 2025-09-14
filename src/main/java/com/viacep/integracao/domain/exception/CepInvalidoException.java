package com.viacep.integracao.domain.exception;

public class CepInvalidoException extends RuntimeException {
    public CepInvalidoException(String message) {
        super(message);
    }
}
