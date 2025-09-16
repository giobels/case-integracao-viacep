package com.viacep.integracao;

import com.viacep.integracao.adapters.dtos.EnderecoResponseDto;
import com.viacep.integracao.application.usecase.BuscarEnderecoPorCEPUseCase;
import com.viacep.integracao.domain.Endereco;
import com.viacep.integracao.domain.exception.EnderecoNaoEncontradoException;
import com.viacep.integracao.domain.valueObject.Cep;
import com.viacep.integracao.infrastructure.EnderecoController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnderecoControllerTest {

    @Mock
    private BuscarEnderecoPorCEPUseCase buscarEnderecoPorCepUseCase;

    @InjectMocks
    private EnderecoController enderecoController;

    @Test
    void deveRetornarEnderecoResponseDto_quandoCepValido() {
        // arrange
        String cepString = "01001000";
        Endereco endereco = new Endereco(
                new Cep(cepString),
                "Praça da Sé",
                "lado ímpar",
                "Sé",
                "São Paulo",
                "SP"
        );

        when(buscarEnderecoPorCepUseCase.buscar(new Cep(cepString))).thenReturn(endereco);

        // act
        ResponseEntity<EnderecoResponseDto> response = enderecoController.buscarEnderecoPorCep(cepString);

        // assert
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().cep()).isEqualTo(cepString);
        assertThat(response.getBody().logradouro()).isEqualTo("praça da sé"); // virou lowercase
        assertThat(response.getBody().bairro()).isEqualTo("Sé");
        assertThat(response.getBody().localidade()).isEqualTo("São Paulo");
        assertThat(response.getBody().uf()).isEqualTo("SP");
    }

    @Test
    void deveRetornarEnderecoResponseDto_quandoCepComHifen() {
        // arrange
        String cepString = "01001-000";
        Endereco endereco = new Endereco(
                new Cep(cepString),
                "Praça da Sé",
                "lado ímpar",
                "Sé",
                "São Paulo",
                "SP"
        );

        when(buscarEnderecoPorCepUseCase.buscar(new Cep(cepString))).thenReturn(endereco);

        // act
        ResponseEntity<EnderecoResponseDto> response = enderecoController.buscarEnderecoPorCep(cepString);

        // assert
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().cep()).isEqualTo("01001000"); // retornou sem hifen
        assertThat(response.getBody().logradouro()).isEqualTo("praça da sé"); // virou lowercase
        assertThat(response.getBody().bairro()).isEqualTo("Sé");
        assertThat(response.getBody().localidade()).isEqualTo("São Paulo");
        assertThat(response.getBody().uf()).isEqualTo("SP");
    }

    @Test
    void deveRetornarExcecao_quandoEnderecoNaoExiste() {
        // arrange
        String cepString = "99999999";
        when(buscarEnderecoPorCepUseCase.buscar(new Cep(cepString))).thenThrow(new EnderecoNaoEncontradoException("Endereço não encontrado para o CEP: " + cepString));

        // act & assert
        assertThrows(EnderecoNaoEncontradoException.class, () -> {
            enderecoController.buscarEnderecoPorCep(cepString);
        });
        assertEquals("Endereço não encontrado para o CEP: " + cepString, assertThrows(EnderecoNaoEncontradoException.class, () -> {
            enderecoController.buscarEnderecoPorCep(cepString);
        }).getMessage());
    }

    @Test
    void deveRetornarExcecao_quandoCepInvalido() {
        // arrange
        String cepString = "12A45-678";

        // act & assert
        assertThrows(Exception.class, () -> {
            enderecoController.buscarEnderecoPorCep(cepString);
        });
    }
}


