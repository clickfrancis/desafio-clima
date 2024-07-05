**CRUD REST API para Dados Meteorológicos**

**Descrição**


> Este projeto é uma API REST que permite cadastrar, consultar, atualizar e deletar dados meteorológicos de algumas cidades. O objetivo principal é testar e demonstrar os conhecimentos adquiridos em desenvolvimento de software, incluindo a utilização de várias tecnologias e práticas de desenvolvimento.

**Instalação**

Pré-requisitos

Certifique-se de ter os seguintes softwares instalados em seu ambiente de desenvolvimento:

Java 17 ou superior
Maven 3.3.1 

**Passo a passo**
Clone o repositório para sua máquina local:

git clone https://github.com/seu-usuario/seu-repositorio.git
Navegue até o diretório do projeto:
```cd seu-repositorio```

Compile o projeto utilizando Maven:
```mvn clean install```

Execute o projeto:
```mvn spring-boot:run```

**Estrutura do Projeto**
O projeto está organizado em duas pastas principais:

> core: Contém a lógica de negócio e as entidades do domínio.
infrastructure: Contém a configuração de infraestrutura, como repositórios e configurações do banco de dados.
Testes

O projeto inclui testes unitários e de integração:

Testes unitários: Utilizando JUnit e Mockito.
Testes de integração: Utilizando MockMvc.

Para executar os testes, utilize o seguinte comando:
```mvn test```

Contribuição
Contribuições são bem-vindas! Siga os passos abaixo para contribuir:

Demais deverão ser feitas em uma nova branch para feature ou correção:

git checkout -b minha-feature
Commit suas alterações:

git commit -m 'Minha nova feature'
Envie para o repositório remoto:

git push origin minha-feature

Abra um Pull Request.

Endpoints Disponíveis
A API fornece os seguintes endpoints para gerenciar os dados meteorológicos:

POST / dados-metereologicos
GET /dados/dados-metereologicos
PUT /dados-metereologicos/atualizar-dados-metereologicos/{cidade}/{data}/{turno}
DELETE /dados-metereologicos/{cidade}/{data}/{turno}

Autor
Francis Santos - Desenvolvedor 
