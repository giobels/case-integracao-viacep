package com.viacep.integracao.application.port;

import com.viacep.integracao.domain.Endereco;
import com.viacep.integracao.domain.exception.EnderecoNaoEncontradoException;
import com.viacep.integracao.domain.valueObject.Cep;


public interface EnderecoGateway  {
    Endereco bucarEnderecoPorCep(Cep cep) throws EnderecoNaoEncontradoException;
}
