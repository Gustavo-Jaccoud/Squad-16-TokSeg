![WhatsApp Image 2025-05-23 at 19 46 08_05f018fa](https://github.com/user-attachments/assets/7cfd8582-810f-4ce5-ab36-bf5a8697bdf9)

<div align="center">
📦 Armário Inteligente 
Sistema de gerenciamento de encomendas automatizado notificações com banco de dados integrado.

📌 Visão Geral
O Armário Inteligente é uma solução que facilita o recebimento e a retirada de encomendas de forma segura e eficiente.

🔒 Acesso controlado por senha

📲 Notificações via e-mail.

🗃️ Banco de dados para registro e histórico de entregas via Docker.

🎯 Funcionalidades
🚪 Acesso Seguro aos Compartimentos
Autenticação por:

🔑 Usuario e Senha

📢 Notificações 
Alertas quando:

📥 Uma encomenda é depositada.

⏳ A encomenda está prestes a vencer o prazo de retirada.

⏳ A encomenda pega pelo adm

❗ Recuperação de senha.

📊 Gestão e Relatórios
   administrativo para:

📅 Visualizar histórico de entregas.

📦 Taxa de ocupação dos compartimentos.

🤖 Automações Avançadas (Futuras atualizações)
   
🖥️ Visão computacional para reconhecer tipo de encomenda.

🤖 Chatbot para consultas via WhatsApp/Telegram.


📊Como executar:
</div>
# Armário Inteligente

## Descrição Geral

Permite o registro, notificação e auditoria de entregas, integrando diferentes perfis de usuários (morador, porteiro, administrador) e mantendo o controle dos compartimentos do armário.

## Requisitos Técnicos

- Java 17 ou superior
- Maven 3.6 ou superior
- Spring Boot 3.x
- Docker
- IDE compatível com Java (recomendado: IntelliJ IDEA ou Eclipse)

## Instalação e Configuração

4. Execute a aplicação:
```bash
# Clone o repositório
$ git clone https://github.com/Gustavo-Jaccoud/Squad-16-TokSeg.git

# Acesse o diretório
$ cd Squad-16-TokSeg

# Execute via Docker Compose
$ docker-compose up --build
```

A aplicação estará disponível em `http://localhost:8080`

---

## Estrutura do Projeto

```
armario-inteligente/
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── .gitignore
├── README.md
└── ...
```

---

## Camadas do Sistema

Swagger (OpenAPI) para documentação da API
## Fluxos Principais

### Cadastro de Encomenda
1. Sistema associa encomenda a um usuário e compartimento.
2. Compartimento é marcado como ocupado.
3. Notificação é enviada ao usuário.


### Gerenciamento de Compartimentos
- Compartimentos podem ser consultados, criados ou removidos.
- Estado de ocupação é atualizado.

## Suporte

Para suporte, entre em contato através de:
- Email: 
- Issues do GitHub:
