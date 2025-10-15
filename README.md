🧩 CEP-API — Consulta de Endereço com API Mockada e Persistência de Logs
📘 Visão Geral
A CEP-API é uma aplicação Spring Boot (Java 17) desenvolvida para realizar consultas de endereços via CEP, acessando uma API externa (ViaCEP) e salvando logs de consulta em um banco de dados PostgreSQL.

Durante os testes, o sistema utiliza WireMock para simular a API externa, permitindo testes offline e previsíveis. O projeto segue princípios SOLID, boas práticas de arquitetura limpa, e inclui testes de integração automatizados.

🧭 Fluxo da Aplicação
O cliente faz uma requisição:

➡️ O Controller chama o Service.
➡️ O Service usa o CepClient para consultar a API (real ou mock).
➡️ A resposta é gravada no banco de dados cep_logger_db.
➡️ E o resultado é retornado ao cliente.

⚙️ Tecnologias Utilizadas
▶️ Como Executar Localmente
🧱 1️⃣ Criar o banco de dados PostgreSQL
⚙️ 2️⃣ Configurar o banco no application.properties
Configuração do banco:

Configuração do JPA:

🧰 3️⃣ Adicionar Lombok
Dependência no pom.xml:

⚠️ O escopo provided indica que o Lombok é necessário apenas em tempo de compilação, não em produção.

🧩 4️⃣ Configuração da IDE
🔹 IntelliJ IDEA

Vá em File → Settings → Plugins.

Busque por “Lombok” e clique em Install.

Em Build, Execution, Deployment → Compiler → Annotation Processors, ative:

✅ “Enable annotation processing”

🔹 Eclipse / STS

Baixe em .

Execute o .jar e aponte para o diretório de instalação do Eclipse.

Reinicie a IDE e confirme que as anotações (@Data, @Builder, etc.) estão habilitadas.

🚀 5️⃣ Rodar a aplicação
🌐 6️⃣ Testar via navegador ou Postman
🧪 7️⃣ Rodar os testes com WireMock
🌍 Endpoints Disponíveis
📄 Exemplo de Retorno
🧾 Banco de Dados
Tabela: log_consulta

🧩 Diagrama da Solução
🌐 Acesso ao Swagger
🔗 

📘 Repositório GitHub
👉 

🚀 Futuras Melhorias e Diferenciais
Funcionalidades planejadas para versões futuras — diferenciais técnicos e boas práticas de infraestrutura.

🐳 Integração com Docker
Containers para PostgreSQL e WireMock usando docker-compose.

Comando: docker-compose up -d

Benefício: Facilita portabilidade e setup do ambiente local (dev/teste/CI/CD).

☁️ Integração com AWS
AWS RDS (PostgreSQL): Persistência de dados em nuvem.

AWS ECS / Elastic Beanstalk: Deploy da aplicação.

AWS CloudWatch: Monitoramento e métricas de performance.

AWS S3: Armazenamento de relatórios e backups dos logs.

✨ Autor: Jurandir Campos

📅 Versão: 1.0.0

📄 Licença: MIT

🧩 Projeto: CEP-API — Mock e Logs com Spring Boot
