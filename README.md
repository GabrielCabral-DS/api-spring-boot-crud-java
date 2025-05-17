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
[<img alt="Warpnet" src="https://sdmntpreastus.oaiusercontent.com/files/00000000-45e0-61f9-a8bf-8918e1e832f5/raw?se=2025-05-15T21%3A19%3A35Z&sp=r&sv=2024-08-04&sr=b&scid=00000000-0000-0000-0000-000000000000&skoid=864daabb-d06a-46b3-a747-d35075313a83&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-05-15T20%3A18%3A29Z&ske=2025-05-16T20%3A18%3A29Z&sks=b&skv=2024-08-04&sig=o3FAq8urL2CQOdEUoAldvhWPahTC6sxhSHeezm3R/6c%3D"/>](SpringBoot)

---

### ✅ Testes Atomatizados CI/CD GitHub Actions! 

[<img alt="Warpnet" src="https://files09.oaiusercontent.com/file-RZSQf6oi9TqSduhXgB5ipr?se=2025-05-17T00%3A09%3A35Z&sp=r&sv=2024-08-04&sr=b&rscc=max-age%3D299%2C%20immutable%2C%20private&rscd=attachment%3B%20filename%3Dcd7c72a1-85a8-4e55-bc1d-f15ea0f5b4e3.png&sig=7SIQxXHl0eZQA2M6OOyW4VPkZc0ZiRgjeksIrXTpw7s%3D"/>](SpringBoot)

---

### ✅ Testes Atomatizados CI/CD GitHub Actions! 

[<img alt="Warpnet" src="https://files09.oaiusercontent.com/file-4DZpZABP7m9eF94Zmn7eoj?se=2025-05-17T00%3A09%3A35Z&sp=r&sv=2024-08-04&sr=b&rscc=max-age%3D299%2C%20immutable%2C%20private&rscd=attachment%3B%20filename%3D2ebb80a7-807e-47e0-9db0-fdfb1546ebfc.png&sig=SVnF4qL1Cb08zos17NM3HSTzRTTv0ROL/MudPCvOWC0%3D"/>](SpringBoot)
