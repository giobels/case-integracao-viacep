package com.viacep.integracao.application.port;

import com.viacep.integracao.domain.Endereco;

import java.util.Optional;

public interface EnderecoGateway {
    Endereco bucarEnderecoPorCep(String CEP);
}
