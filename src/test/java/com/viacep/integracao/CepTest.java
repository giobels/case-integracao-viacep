package com.viacep.integracao;

import com.viacep.integracao.domain.exception.CepInvalidoException;
import com.viacep.integracao.domain.valueObject.Cep;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CepTest {

    @Test
    void deveCriarCepValido() {
        Cep cep = new Cep("12345-678");
        assertEquals("12345678", cep.getValor());
    }

    @Test
    void deveLancarExcecaoParaCepInvalido() {
        assertThrows(CepInvalidoException.class, () -> new Cep("1234"));
    }

    @Test
    void deveLancarExcecaoParaCepComLetras() {
        assertThrows(CepInvalidoException.class, () -> new Cep("12A45-678"));
    }
}

