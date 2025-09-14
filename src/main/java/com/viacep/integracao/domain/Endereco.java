package com.viacep.integracao.domain;

import com.viacep.integracao.domain.valueObject.Cep;

public class Endereco {
    private Cep cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;


    public Endereco (Cep cep, String logradouro, String complemento, String bairro, String localidade, String uf){
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    public Cep getCep() { return cep; }
    public String getLogradouro() { return logradouro; }
    public String getComplemento() { return complemento; }
    public String getBairro() { return bairro; }
    public String getLocalidade() { return localidade; }
    public String getUf() { return uf; }
}
