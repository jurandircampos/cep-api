🧩 CEP-API — Consulta de Endereço com API Mockada e Persistência de Logs














📘 Visão Geral

A CEP-API é uma aplicação Spring Boot (Java 17) desenvolvida para realizar consultas de endereços via CEP, acessando uma API externa (ViaCEP) e salvando logs de consulta em um banco de dados PostgreSQL.

Durante os testes, o sistema utiliza WireMock para simular a API externa, permitindo testes offline e previsíveis.
O projeto segue princípios SOLID, boas práticas de arquitetura limpa, e inclui testes de integração automatizados.

🧭 Fluxo da Aplicação

O cliente faz uma requisição:

GET /api/cep/{cep}


➡️ O Controller chama o Service,
➡️ O Service usa o CepClient para consultar a API (real ou mock),
➡️ A resposta é gravada no banco de dados cep_logger_db,
➡️ E o resultado é retornado ao cliente.

⚙️ Tecnologias Utilizadas
Tecnologia	Função
☕ Java 17	Linguagem principal
🌱 Spring Boot 3.x	Framework backend
🧩 Spring Data JPA	Persistência ORM
🐘 PostgreSQL	Banco de dados relacional
🧪 WireMock 3.6.0	Mock da API externa
🧷 JUnit 5	Testes automatizados
✂️ Lombok	Redução de código boilerplate
⚙️ Maven	Gerenciador de dependências
▶️ Como Executar Localmente
🧱 1️⃣ Criar o banco de dados PostgreSQL
CREATE DATABASE cep_logger_db;

⚙️ 2️⃣ Configurar o banco no application.properties
# Configuração do banco
spring.datasource.url=jdbc:postgresql://localhost:5433/cep_logger_db
spring.datasource.username=postgres
spring.datasource.password=11111

# Configuração do JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

🧰 3️⃣ Adicionar Lombok
📦 Dependência no pom.xml
<!-- Lombok - Reduz código boilerplate -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.32</version>
    <scope>provided</scope>
</dependency>


⚠️ O escopo provided indica que o Lombok é necessário apenas em tempo de compilação, não em produção.

🧩 4️⃣ Configuração da IDE
🔹 IntelliJ IDEA

Vá em File → Settings → Plugins

Busque por “Lombok”

Clique em Install

Em Build, Execution, Deployment → Compiler → Annotation Processors, ative:
✅ “Enable annotation processing”

🔹 Eclipse / STS

Baixe em https://projectlombok.org/download

Execute o .jar e aponte para o diretório do Eclipse

Reinicie a IDE

Confirme que as anotações (@Data, @Builder, etc.) estão habilitadas

🚀 5️⃣ Rodar a aplicação
mvn spring-boot:run

🌐 6️⃣ Testar via navegador ou Postman
GET http://localhost:8080/api/cep/18040260

🧪 7️⃣ Rodar os testes com WireMock
mvn test

🌍 Endpoints Disponíveis
Método	Endpoint	Descrição
GET	/api/cep/{cep}	Busca o CEP, retorna dados e grava log no banco
📄 Exemplo de Retorno
{
  "cep": "18040-260",
  "logradouro": "Rua Bernardino Telles de Medeiros",
  "bairro": "Vila São João",
  "localidade": "Sorocaba",
  "uf": "SP",
  "estado": "São Paulo",
  "regiao": "Sudeste",
  "ibge": "3552205",
  "gia": "6695",
  "ddd": "15",
  "siafi": "7145"
}

🧾 Banco de Dados

Tabela: log_consulta

Campo	Tipo	Descrição
id	BIGSERIAL	Identificador único
cep	VARCHAR(9)	CEP consultado
retorno	JSON	Dados retornados da API
data_hora	TIMESTAMP	Data e hora da consulta
🧩 Diagrama da Solução
flowchart TD

A[Cliente / Postman / Navegador] -->|GET /api/cep/{cep}| B[Controller: CepController]
B --> C[Service: CepService]
C --> D[CepClient (RestTemplate)]
D -->|Consulta externa| E[API Mockada (WireMock) / ViaCEP]
C --> F[(Banco de Dados)]
E --> D
D --> C
C --> B
B --> A

subgraph LOG
F[(Tabela: log_consulta)]
end

🌐 Acesso ao Swagger

🔗 http://localhost:8080/swagger-ui/index.html

📘 Repositório GitHub

👉 https://github.com/jurandircampos/cep-api

🚀 Futuras Melhorias e Diferenciais

🔮 Funcionalidades planejadas para versões futuras — diferenciais técnicos e boas práticas de infraestrutura.

🐳 Integração com Docker

Containers para PostgreSQL e WireMock usando docker-compose:

docker-compose up -d


Facilita portabilidade e setup do ambiente local (dev/teste/CI/CD).

☁️ Integração com AWS

AWS RDS (PostgreSQL) → persistência em nuvem.

AWS ECS / Elastic Beanstalk → deploy da aplicação.

AWS CloudWatch → monitoramento e métricas.

AWS S3 → armazenamento de relatórios e backups dos logs.

✨ Autor: Jurandir Campos

📅 Versão: 1.0.0
📄 Licença: MIT
🧩 Projeto: CEP-API — Mock e Logs com Spring Boot
