# cep-api
🧩 Projeto CEP — Consulta de Endereço com API Mockada e Persistência de Logs

📘 Visão Geral

Esta aplicação Spring Boot (Java 17) realiza a busca de endereços via CEP, consultando uma API externa (ViaCEP) e salvando logs das consultas em banco de dados.
Durante os testes, a API externa é simulada com WireMock, garantindo previsibilidade e independência da internet.

O projeto utiliza de boas práticas de arquitetura, uso de SOLID e testes de integração automatizados.

O cliente faz uma requisição GET /api/cep/{cep}.
O controller chama o service.
O service usa o CepClient para acessar a API (real ou mock).
A resposta é retornada e salva no banco (cep_logger_db).
O serviço retorna o JSON ao cliente.

⚙️ Tecnologias Utilizadas

Tecnologia	            Função
Java 17	                Linguagem principal
Spring Boot 3.x	        Framework backend
Spring Data JPA	        Persistência ORM
PostgreSQL	            Banco de dados
WireMock 3.6.0	        Mock da API externa
JUnit 5	                Testes automatizados
Maven	                Gerenciador de dependências

▶️ Como Executar Localmente
    #Criar o banco de dados PostgreSQL
    
    Create database cep_logger_db;

1️⃣ Configurar banco PostgreSQL

# Configuração do banco PostgreSQL

spring.datasource.url=jdbc:postgresql://localhost:5433/cep_logger_db
spring.datasource.username=postgres
spring.datasource.password=11111 

# Configuração do JPA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

⚙️ Dependência Lombok

O projeto utiliza o Project Lombok
 para simplificar o código, eliminando a necessidade de escrever manualmente getters, setters, construtores e outros métodos comuns.

📦 Adicionando ao pom.xml
<!-- Lombok - Reduz código boilerplate -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.32</version>
    <scope>provided</scope>
</dependency>


⚠️ Observação:
O escopo provided indica que o Lombok é necessário apenas em tempo de compilação, não em produção.

🧰 Configuração da IDE
🔹 IntelliJ IDEA

Vá em File → Settings → Plugins

Busque por “Lombok”


Clique em Install

Em Settings → Build, Execution, Deployment → Compiler → Annotation Processors, ative:
✅ “Enable annotation processing”

🔹 Eclipse / STS

Baixe o instalador do Lombok em https://projectlombok.org/download

Execute o .jar e aponte para o diretório do Eclipse.

Reinicie a IDE.

Verifique se há suporte a anotações (Ex: @Data, @Builder, @Getter, @Setter, @NoArgsConstructor, etc.).
2️⃣ Rodar aplicação
mvn spring-boot:run

3️⃣ Testar via navegador ou Postman
GET http://localhost:8080/api/cep/18040260

4️⃣ Rodar os testes com WireMock
mvn test

🌐 Endpoints
Método	Endpoint	Descrição
GET	/api/cep/{cep}	Busca o CEP, retorna dados e salva log

#Exemplo
http://localhost:8080/api/cep/18040260

#Retorno de dados
{
"cep": "18040-260",
"logradouro": "Rua Bernardino Telles de Medeiros",
"complemento": "",
"unidade": "",
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

🌐 Acesso ao Swagger 
http://localhost:8080/swagger-ui/index.html

📘 Repositório GitHub

🔗 https://github.com/jurandircampos/cep-api

🚀 Futuras Melhorias e Diferenciais

🔮 Estas funcionalidades ainda não foram implementadas, mas fazem parte da visão futura do projeto e representam diferenciais técnicos que poderão ser agregados em versões seguintes:

🐳 Integração com Docker
Criação de containers para o PostgreSQL e o WireMock, utilizando docker-compose para subir todo o ambiente local com um único comando:

    docker-compose up -d

Facilitar a portabilidade e o setup do projeto em diferentes ambientes (dev, teste, CI/CD).

☁️ Integração com AWS

Implantação futura na AWS para estudo de cloud:
AWS RDS (PostgreSQL) → persistência em nuvem.
AWS ECS ou Elastic Beanstalk → deploy da aplicação Spring Boot.
AWS CloudWatch → monitoramento e logs das consultas de CEP.
AWS S3 → armazenamento de relatórios e exportações dos logs.# cep-api
