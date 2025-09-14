package com.viacep.integracao.infrastructure.viacep;

import com.viacep.integracao.application.port.EnderecoGateway;
import com.viacep.integracao.domain.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViaCepGatewayImpl implements EnderecoGateway {
    @Autowired
    private ViaCepClient viacepClient;

    @Override
    public Endereco bucarEnderecoPorCep(String cep) {
        ViaCepResponseDto viaCepResponse =  viacepClient.buscarEnderecoPorCep(cep);

        return new Endereco(
                viaCepResponse.cep(),
                viaCepResponse.logradouro(),
                viaCepResponse.complemento(),
                viaCepResponse.bairro(),
                viaCepResponse.localidade(),
                viaCepResponse.uf()
        );

    }

}
