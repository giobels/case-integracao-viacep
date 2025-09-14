package com.viacep.integracao.infrastructure;

import com.viacep.integracao.adapters.dtos.EnderecoResponseDto;
import com.viacep.integracao.application.usecase.BuscarEnderecoPorCEPUseCase;
import com.viacep.integracao.domain.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private BuscarEnderecoPorCEPUseCase buscarEnderecoPorCepUseCase;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoResponseDto> buscarEnderecoPorCep( @PathVariable String cep) {
        Endereco endereco = buscarEnderecoPorCepUseCase.buscar(cep);
        EnderecoResponseDto responseDto = new EnderecoResponseDto(
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getLocalidade(),
                endereco.getUf()
        );
        return ResponseEntity.ok(responseDto);
    }
}
