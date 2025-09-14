package com.viacep.integracao.infrastructure.config;

import com.viacep.integracao.application.port.EnderecoGateway;
import com.viacep.integracao.application.usecase.BuscarEnderecoPorCEPUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public BuscarEnderecoPorCEPUseCase buscarEnderecoPorCepUseCase(EnderecoGateway gateway) {
        return new BuscarEnderecoPorCEPUseCase(gateway);
    }
}
