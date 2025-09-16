package com.viacep.integracao.adapters.dtos;

public record EnderecoResponseDto (
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf
){}
