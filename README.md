🧩 CEP-API — Consulta de Endereço com API Mockada e Persistência de Logs 📘
Visão Geral
A CEP-API é uma aplicação Spring Boot (Java 17) que permite consulta de endereços via CEP, utilizando a API externa ViaCEP e armazenando logs dessas consultas em um banco PostgreSQL.

Durante testes, o sistema usa WireMock para simular a API externa, permitindo testes offline e previsíveis. O projeto segue os princípios SOLID, arquitetura limpa e inclui testes de integração automatizados.

🧭 Fluxo da Aplicação
O cliente faz uma requisição.

O Controller chama o Service.

O Service usa o CepClient para consultar a API (real ou mock).

A resposta é salva no banco de dados cep_logger_db.

O resultado é retornado ao cliente.

⚙️ Tecnologias Utilizadas
Java 17

Spring Boot

PostgreSQL

WireMock

Lombok

Maven

Swagger

▶️ Como Executar Localmente
Criar o banco de dados PostgreSQL com o nome cep_logger_db.

Configurar o banco no application.properties com a URL, usuário, senha e configurações JPA adequadas.

Adicionar a dependência Lombok no pom.xml, com escopo provided.

Configurar a IDE para habilitar processamento de anotações Lombok:

Em IntelliJ IDEA: instalar plugin Lombok e ativar ‘Enable annotation processing’.

Em Eclipse/STS: instalar plugin Lombok e reiniciar IDE.

Rodar a aplicação pela IDE ou pelo comando Maven ./mvnw spring-boot:run.

Testar a API via navegador ou Postman nos endpoints disponíveis.

Rodar os testes automatizados com WireMock usando ./mvnw test.

🌍 Endpoints Disponíveis
Consulte a documentação Swagger acessando /swagger-ui.html na aplicação.

📄 Exemplo de Retorno
Exemplo típico de resposta JSON contendo dados de endereço a partir do CEP:

CEP: 01001-000
Logradouro: Praça da Sé
Complemento: lado ímpar
Bairro: Sé
Localidade: São Paulo
UF: SP
IBGE: 3550308
GIA: 1004
DDD: 11
SIAFI: 7107

🧾 Banco de Dados
A tabela log_consulta armazena os logs das consultas de CEP feitas pela aplicação.

🧩 Diagrama da Solução
O diagrama apresenta o fluxo da aplicação entre cliente, controller, service, client externo (real ou mock) e banco de dados.

🌐 Acesso ao Swagger
A interface de documentação está disponível em http://localhost:8080/swagger-ui.html.

📘 Repositório GitHub
O código completo está disponível no repositório:

https://github.com/seuusuario/cep-api

🚀 Futuras Melhorias e Diferenciais
Integração com Docker via docker-compose para containers PostgreSQL e WireMock, facilitando setup local e CI/CD.

Integração com serviços AWS:

RDS para banco de dados na nuvem.

ECS ou Elastic Beanstalk para deploy da aplicação.

CloudWatch para monitoramento e métricas.

S3 para armazenamento de relatórios e backups.

✨ Autor
Jurandir Campos

📅 Versão
1.0.0

📄 Licença
MIT License
