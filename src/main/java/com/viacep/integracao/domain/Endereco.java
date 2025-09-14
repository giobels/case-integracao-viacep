package com.viacep.integracao.domain;

public class Endereco {
    private String CEP;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;


    public Endereco (String CEP, String logradouro, String complemento, String bairro, String localidade, String uf){
        this.CEP = CEP;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    public String getCep() { return CEP; }
    public String getLogradouro() { return logradouro; }
    public String getComplemento() { return complemento; }
    public String getBairro() { return bairro; }
    public String getLocalidade() { return localidade; }
    public String getUf() { return uf; }
}
