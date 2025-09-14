package com.viacep.integracao.domain.valueObject;

import com.viacep.integracao.domain.exception.CepInvalidoException;

public class Cep {
    private final String valor;

    public Cep(String valor) {
        String somenteDigitos = valor.replaceAll("\\D", "");
        if (somenteDigitos.length() != 8) {
            throw new CepInvalidoException("CEP deve conter 8 caracteres numéricos");
        }
        this.valor = somenteDigitos;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
