###  📝 CartCheck

API REST em Java Spring para cadastro e gerenciamento de produtos. O sistema possui dois perfis de usuários: ADMIN, com permissão total (CRUD), e FUNCIONÁRIO, com permissão apenas de leitura (listar e buscar).

:large_blue_circle: [Linkedin](https://www.linkedin.com/in/gabriel-cabral-878482262/)

### ⚙️ Funcionalidades

- [x] Cadastro de produtos (nome, preço, quantidade, categoria)
- [x] Cadastro de usuarios (nome, email, senha, telefone, endereço, cidade, estado e roles[ADMIN, USER])
- [x] Atualização de produtos (apenas ADMIN)
- [x] Exclusão de produtos (apenas ADMIN)
- [X] Listagem de todos os produtos (admin e funcionário)
- [X] Busca de produto por ID ou nome (admin e funcionário)
- [X] Controle de acesso com Spring Security (roles ADMIN e FUNCIONARIO)
- [X] Documentação da API com Swagger/OpenAPI
- [X] Validações de entrada
- [X] Conversão entre entidade e DTO com MapStruct
---
### 🧱 Arquitetura do Projeto
- Arquitetura em camadas
- Controller: Recebe e responde requisições HTTP
- Service: Regras de negócio
- Repository: Acesso ao banco de dados
- Entity/DTO: Estrutura de dados
- Segurança baseada em roles (ADMIN, FUNCIONARIO)
- Injeção de dependência com Spring
---
### ⚙️ Tecnologias Utilizadas
- Linguagem: Java 21
- Framework: Spring Boot
- Banco de dados: PostgreSQL
- ORM: Spring Data JPA
- Segurança: Spring Security
- Mapeamento DTO: MapStruct
- Build: Maven
- Testes: JUnit 5, Mockito
- Documentação: Swagger/OpenAPI
- Controle de Versão: Git + GitHub

### 🗃️ Arquitetura do Banco de Dados
[<img alt="Warpnet" src=""/>](SpringBoot)

---
