# Case de Integração ViaCep

Serviço de integração com a API pública [ViaCEP](https://viacep.com.br/) para consulta de endereços a partir de CEPs.  
O projeto segue princípios de **Domain-Driven Design (DDD)**, separando camadas de domínio, aplicação e infraestrutura.

---

## Arquitetura

A aplicação foi construída para **isolar a lógica de domínio** da lógica de integração, utilizando o padrão **Gateway** para abstrair chamadas externas.

Fluxo de execução:
1. O usuário faz uma requisição HTTP informando o CEP.
2. O **Controller** chama o caso de uso `BuscarEnderecoPorCepUseCase`.
3. O **Use Case** delega a busca ao `EnderecoGateway`.
4. O `EnderecoGateway` é implementado pela `ViaCepGatewayImpl`, que usa um **Feign Client** para consumir a API pública.
5. O resultado é transformado de `ViaCepResponseDto` (infra) para o objeto de domínio `Endereco`.
6. O objeto `Endereco` é mapeado para a `EnderecoResponseDto` e retornado pela aplicação.

---

## Camadas do projeto

- **domain**  
  - Entidades e objetos de valor (`Endereco`, `Cep`)  
  - Exceções de domínio (`CepInvalidoException`, `EnderecoNaoEncontradoException`)  
  - Contratos (`EnderecoGateway`)  

- **application**  
  - Casos de uso (`BuscarEnderecoPorCepUseCase`)  
  - Orquestração do fluxo entre controller e domínio  

- **infrastructure**  
  - Integração com ViaCEP (`ViaCepClient`, `ViaCepResponseDto`)  
  - Implementação do gateway (`ViaCepGatewayImpl`)  
  - Controllers e adapters de entrada/saída  

---

## Decisões de design

- **VO `Cep`**: encapsula validação do formato, garantindo que apenas CEPs válidos circulam no domínio.  
- **Gateway**: abstrai dependências externas, permitindo trocar o provedor sem impactar o domínio.  
- **DTOs**: existem em duas camadas:  
  - `ViaCepResponseDto` (infra) → reflete contrato do ViaCEP.  
  - `EnderecoResponseDto` (application/presentation) → contrato exposto pela aplicação.  
- **Exceptions**: problemas conceituais, como CEP inválido e Endereco inxistente.

---

## Como rodar no IntelliJ IDEA

1. **Clone o repositório**
   ```bash
   git clone https://github.com/giobels/case-integracao-viacep.git
2. Abra o projeto no IntelliJ e configure o SDK
  - Defina o SKD como Java 23 utilizado no projeto
3. Rodar a aplicação
  - Localize a classe principal (IntegracaoViaCepApplication).
  - Clique com o botão direito sobre ela → Run

A aplicação estará disponível em:
```bash
http://localhost:8080/enderecos/{cep}
```

Exemplo de chamada:
```bash
curl http://localhost:8080/enderecos/01001000
```
Resposta esperada:
```bash
{
  "cep": "01001000",
  "logradouro": "praça da sé",
  "complemento": "lado ímpar",
  "bairro": "Sé",
  "localidade": "São Paulo",
  "uf": "SP"
}
```

