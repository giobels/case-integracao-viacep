package com.viacep.integracao.infrastructure.viacep;

import com.viacep.integracao.application.port.EnderecoGateway;
import com.viacep.integracao.domain.Endereco;
import com.viacep.integracao.domain.exception.EnderecoNaoEncontradoException;
import com.viacep.integracao.domain.valueObject.Cep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Component
public class ViaCepGatewayImpl implements EnderecoGateway {
    @Autowired
    private ViaCepClient viacepClient;

    @Override
    public Endereco bucarEnderecoPorCep(Cep cep) {
        try{
        ViaCepResponseDto viaCepResponse =  viacepClient.buscarEnderecoPorCep(cep.getValor());

        return new Endereco(
                new Cep(viaCepResponse.cep()),
                viaCepResponse.logradouro(),
                viaCepResponse.complemento(),
                viaCepResponse.bairro(),
                viaCepResponse.localidade(),
                viaCepResponse.uf()
        );
        } catch (Exception e) {
            throw new EnderecoNaoEncontradoException("Endereço não encontrado para o CEP: " + cep.getValor());
        }
    }
}
