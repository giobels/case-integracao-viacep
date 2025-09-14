package com.viacep.integracao.application.usecase;

import com.viacep.integracao.application.port.EnderecoGateway;
import com.viacep.integracao.domain.Endereco;

public class BuscarEnderecoPorCEPUseCase {
    private final EnderecoGateway gateway;

    public BuscarEnderecoPorCEPUseCase(EnderecoGateway gateway) {
        this.gateway = gateway;
    }

    public Endereco buscar(String cep) {
        return gateway.bucarEnderecoPorCep(cep);
    }
}
