package com.viacep.integracao.application.usecase;

import com.viacep.integracao.domain.exception.CepInvalidoException;
import com.viacep.integracao.application.port.EnderecoGateway;
import com.viacep.integracao.domain.Endereco;
import com.viacep.integracao.domain.exception.EnderecoNaoEncontradoException;
import com.viacep.integracao.domain.valueObject.Cep;

public class BuscarEnderecoPorCEPUseCase {
    private final EnderecoGateway gateway;

    public BuscarEnderecoPorCEPUseCase(EnderecoGateway gateway) {
        this.gateway = gateway;
    }

    public Endereco buscar(Cep cep) throws EnderecoNaoEncontradoException {
        return gateway.bucarEnderecoPorCep(cep);
    }
}
