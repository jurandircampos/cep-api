# 🧩 **CEP-API — Consulta de Endereço com API Mockada e Persistência de Logs**

![Java](https://img.shields.io/badge/Java-17-red?logo=openjdk\&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3-brightgreen?logo=springboot\&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql\&logoColor=white)
![WireMock](https://img.shields.io/badge/WireMock-3.6-orange?logo=java\&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-Enabled-purple?logo=java\&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-UI-green?logo=swagger\&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

---

## 📘 **Visão Geral**

A **CEP-API** é uma aplicação **Spring Boot (Java 17)** que permite a **consulta de endereços via CEP**, utilizando a **API externa ViaCEP** e armazenando **logs das consultas** em um banco de dados **PostgreSQL**.

Durante os testes, o sistema utiliza o **WireMock** para simular a API externa, permitindo **testes offline e previsíveis**.
O projeto segue os **princípios SOLID**, aplica **arquitetura limpa** e inclui **testes de integração automatizados**.

---

## 🧭 **Fluxo da Aplicação**

1. O cliente faz uma requisição `GET /api/cep/{cep}`
2. O **Controller** recebe a requisição e chama o **Service**
3. O **Service** utiliza o **CepClient** para consultar a API (real ou mock)
4. A resposta é salva no banco de dados `cep_logger_db`
5. O resultado é retornado ao cliente no formato **JSON**

---

## ⚙️ **Tecnologias Utilizadas**

| Tecnologia         | Função                         |
| ------------------ | ------------------------------ |
| ☕ **Java 17**      | Linguagem principal            |
| 🌱 **Spring Boot** | Framework backend              |
| 🐘 **PostgreSQL**  | Banco de dados relacional      |
| 🧪 **WireMock**    | Mock da API externa            |
| ✂️ **Lombok**      | Redução de código boilerplate  |
| ⚙️ **Maven**       | Gerenciador de dependências    |
| 📘 **Swagger**     | Documentação interativa da API |

---

## ▶️ **Como Executar Localmente**

### 🧱 **1️⃣ Criar o Banco de Dados**

Execute no PostgreSQL:

```sql
CREATE DATABASE cep_logger_db;
```

---

### ⚙️ **2️⃣ Configurar o Banco no `application.properties`**

```properties
# Configuração do banco de dados
spring.datasource.url=jdbc:postgresql://localhost:5433/cep_logger_db
spring.datasource.username=postgres
spring.datasource.password=11111

# Configuração do JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

### ✂️ **3️⃣ Adicionar Lombok**

📄 `pom.xml`

```xml
<!-- Lombok - Reduz código boilerplate -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.32</version>
    <scope>provided</scope>
</dependency>
```

> ⚠️ O escopo `provided` indica que o Lombok é necessário apenas em tempo de compilação, não em produção.

---

### 💡 **4️⃣ Configurar a IDE**

#### 🔹 **IntelliJ IDEA**

1. Vá em **File → Settings → Plugins**
2. Busque por **“Lombok”** e instale
3. Vá em **Build, Execution, Deployment → Compiler → Annotation Processors**
4. Ative ✅ *“Enable annotation processing”*

#### 🔹 **Eclipse / STS**

1. Baixe em [https://projectlombok.org/download](https://projectlombok.org/download)
2. Execute o `.jar` e aponte para o diretório da IDE
3. Reinicie o Eclipse
4. Verifique se as anotações (`@Data`, `@Getter`, `@Builder`, etc.) são reconhecidas

---

### 🚀 **5️⃣ Rodar a Aplicação**

```bash
./mvnw spring-boot:run
```

---

### 🌐 **6️⃣ Testar a API**

Use o navegador ou **Postman**:

```
GET http://localhost:8080/api/cep/18040260
```

---

### 🧪 **7️⃣ Rodar os Testes com WireMock**

```bash
./mvnw test
```

---

## 🌍 **Endpoints Disponíveis**

A documentação completa está disponível via **Swagger**:
🔗 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

| Método  | Endpoint         | Descrição                                             |
| ------- | ---------------- | ----------------------------------------------------- |
| **GET** | `/api/cep/{cep}` | Consulta o CEP, retorna os dados e salva log no banco |

---

## 📄 **Exemplo de Retorno**

```json
{
  "cep": "01001-000",
  "logradouro": "Praça da Sé",
  "complemento": "lado ímpar",
  "bairro": "Sé",
  "localidade": "São Paulo",
  "uf": "SP",
  "ibge": "3550308",
  "gia": "1004",
  "ddd": "11",
  "siafi": "7107"
}
```

---

## 🧾 **Banco de Dados**

A tabela `cep_query_log` armazena os logs das consultas de CEP feitas pela aplicação:

| Campo       | Tipo       | Descrição               |
| ----------- | ---------- | ----------------------- |
| `id`        | BIGSERIAL  | Identificador único     |
| `cep`       | VARCHAR(9) | CEP consultado          |
| `erro`      | JSON       | Dados retornados da API |
| `data_hora` | TIMESTAMP  | Data e hora da consulta |
---etc

## 🧩 **Diagrama da Solução**

flowchart TD

A[Cliente / Postman / Navegador] -->|GET /api/cep/{cep}| B[Controller: CepController]
B --> C[Service: CepService]
C --> D[CepClient (RestTemplate)]
D -->|Consulta externa| E[API Mockada (WireMock) / ViaCEP]
C --> F[(Banco de Dados)]
F -->|Grava log| C
C --> B
B --> A


## 🌐 **Acesso ao Swagger**

📘 Interface de documentação:
👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 📘 **Repositório GitHub**

🔗 [https://github.com/jurandircampos/cep-api](https://github.com/jurandircampos/cep-api)

---

## 🚀 **Futuras Melhorias e Diferenciais**

> 🔮 Funcionalidades planejadas para versões futuras — diferenciais técnicos e boas práticas de infraestrutura.

### 🐳 **Integração com Docker**

* Criação de containers para **PostgreSQL** e **WireMock**, usando `docker-compose`:

  ```bash
  docker-compose up -d
  ```
* Facilita o setup local e pipelines de **CI/CD**.

### ☁️ **Integração com AWS**

* **AWS RDS (PostgreSQL)** → persistência na nuvem
* **AWS ECS / Elastic Beanstalk** → deploy automatizado
* **AWS CloudWatch** → logs e monitoramento
* **AWS S3** → armazenamento de relatórios e backups

---

## ✨ **Autor**

**Jurandir Campos**
📅 Versão: `1.0.0`
📄 Licença: `MIT License`

---

💡 *“A melhor arquitetura é aquela que permite testar, evoluir e compreender facilmente.”* 🧠
