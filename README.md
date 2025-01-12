# 🌱 Agrix - Gerenciamento de Fazendas(Back-end)

O **Agrix** é um sistema de gerenciamento de fazendas e suas plantações, com funcionalidades que evoluíram ao longo de três fases de desenvolvimento. O projeto foi desenvolvido utilizando **Spring Boot** e **Docker**, com implementação de autenticação JWT e controle de acesso baseado em roles. A solução é projetada para auxiliar na gestão de fazendas, controle de plantações, gerenciamento de fertilizantes e usuários, com foco na escalabilidade e segurança.

## Funcionalidades

### Fase A
- **Cadastro e Listagem de Fazendas e Plantações**: Cadastro de fazendas e plantações, com endpoints para listar e buscar informações detalhadas sobre fazendas e plantações.
- **Dockerização**: Configuração de Docker para facilitar a execução e distribuição da aplicação.
- **Tratamento de Erros**: Implementação de um controlador global de erros.

### Fase B
- **Migração de Código**: Código da Fase A migrado para um novo projeto com manutenção das funcionalidades.
- **Ajuste de Rotas com Datas**: Suporte a datas nas rotas relacionadas às plantações e nova rota de busca por data de colheita.
- **Gerenciamento de Fertilizantes**: Funcionalidade para criar, listar, e associar fertilizantes às plantações.
- **Testes de Unidade**: Implementação de testes com cobertura mínima de 80%.

### Fase C
- **Migração de Código**: Código da Fase B migrado com manutenção das funcionalidades anteriores.
- **Autenticação e Controle de Acesso**: Implementação de autenticação com JWT e controle de acesso a rotas com base em roles (USER, MANAGER, ADMIN).
- **Criação de Pessoas**: Rota para cadastrar novos usuários no sistema.
- **Limitação de Acesso**: Controle de acesso à rota GET /fertilizers, permitindo acesso apenas para usuários com role ADMIN.

## Stacks Utilizadas
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## Endpoints da API

### **🏠 Fazenda (`/farms`)**

- **POST /farms**: Cria uma nova fazenda.
- **GET /farms**: Lista todas as fazendas cadastradas.
- **GET /farms/{id}**: Obtém informações detalhadas de uma fazenda.
- **PUT /farms/{id}**: Atualiza os dados de uma fazenda.
- **DELETE /farms/{id}**: Deleta uma fazenda.

### **🌱 Plantações (`/crops`)**

- **POST /farms/{farmId}/crops**: Adiciona uma nova plantação a uma fazenda.
- **GET /farms/{farmId}/crops**: Lista as plantações de uma fazenda.
- **GET /crops**: Lista todas as plantações.
- **GET /crops/{id}**: Obtém informações sobre uma plantação.
- **PUT /crops/{id}**: Atualiza os dados de uma plantação.
- **DELETE /crops/{id}**: Deleta uma plantação.
- **GET /crops/search**: Busca plantações pela data de colheita.

### **💩 Fertilizantes (`/fertilizers`)**

- **POST /fertilizers**: Cria um novo fertilizante.
- **GET /fertilizers**: Lista todos os fertilizantes cadastrados. **Restrito a usuários com role ADMIN**.
- **GET /fertilizers/{id}**: Detalha informações sobre um fertilizante.
- **PUT /fertilizers/{id}**: Atualiza os dados de um fertilizante.
- **DELETE /fertilizers/{id}**: Deleta um fertilizante.
- **POST /crops/{cropId}/fertilizers/{fertilizerId}**: Associa um fertilizante a uma plantação.
- **GET /crops/{cropId}/fertilizers**: Lista os fertilizantes de uma plantação.

### **👤 Pessoas (`/persons`)**

- **POST /persons**: Cria uma nova pessoa (usuário).
- **GET /persons**: Lista todas as pessoas cadastradas.
- **GET /persons/{id}**: Obtém informações detalhadas de uma pessoa.
- **PUT /persons/{id}**: Atualiza os dados de uma pessoa.
- **DELETE /persons/{id}**: Deleta uma pessoa.

### **🔑 Autenticação (`/auth`)**

- **POST /auth/login**: Realiza a autenticação de um usuário e retorna um token JWT.

### **Controle de Acesso**
- **GET /farms**: Restrito a usuários autenticados com as roles USER, MANAGER ou ADMIN.
- **GET /crops**: Restrito a usuários com roles MANAGER ou ADMIN.
- **GET /fertilizers**: Restrito a usuários com role ADMIN (retorna **403 Forbidden** caso o usuário não tenha permissão).

---

## Acesso ao Projeto

O projeto foi implantado e está disponível no Fly.io. Você pode acessá-lo diretamente através do seguinte link:

[https://projeto-agrix.fly.dev/](https://projeto-agrix.fly.dev/)

Esses endpoints podem ser testados em um cliente como **Insomnia** ou **Postman** usando o link fornecido para realizar requisições aos recursos da API.

---

## Arquitetura do Banco de Dados

Abaixo está a representação visual da arquitetura do banco de dados que sustenta o sistema Agrix.

![Arquitetura do Banco de Dados](link-da-imagem-arquitetura-db.png)

---

## Aprendizados e Desenvolvimento

Durante o desenvolvimento do Agrix, foram adquiridos conhecimentos sobre:

- Desenvolvimento de APIs RESTful com Spring Boot.
- Autenticação e Autorização utilizando JWT e Spring Security.
- Gerenciamento de dados e relacionamento de entidades com Spring Data JPA.
- Dockerização de aplicações para facilitar a execução e distribuição.
- **Deploy de aplicações**: Como realizar o deploy de uma aplicação Spring Boot no **Fly.io**, garantindo alta disponibilidade e escalabilidade na nuvem.
- **CI/CD (Integração Contínua e Entrega Contínua)**: Como configurar um pipeline de **CI/CD** para automatizar os testes, build e deploy do projeto utilizando ferramentas como **GitHub Actions**, permitindo que alterações no código sejam testadas e implantadas automaticamente no ambiente de produção.

## Agradecimentos

Obrigado por conferir o projeto Agrix! Ele foi desenvolvido para consolidar conhecimentos em desenvolvimento de APIs com Spring Boot, Docker, e segurança em aplicações. Qualquer dúvida ou sugestão será bem-vinda!

**Autor**: [Rodrigo Cesar Christofani Junior](https://github.com/Christofani)

