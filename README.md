![logo tokseg](https://github.com/user-attachments/assets/7cfd8582-810f-4ce5-ab36-bf5a8697bdf9)
<div align="center">
   
  # 📦 Tokseg Storage
</div>
Sistema robusto de gerenciamento de encomendas projetado para otimizar o fluxo de recebimento, armazenamento seguro e retirada em ambientes como condomínios e empresas. Oferece acesso seguro, notificações automatizadas por e-mail e uma infraestrutura de banco de dados conteinerizada com Docker.

---

## ✅ Visão Geral

O *Tokseg Storage* é uma aplicação web desenvolvida para automatizar e trazer mais segurança ao processo de gestão de encomendas. Ele controla desde o momento em que a encomenda é recebida e armazenada em um compartimento específico, até a notificação do destinatário e a confirmação da retirada, mantendo um histórico completo para auditoria.

---

## 🚀 Funcionalidades

-   *Autenticação Segura:* Login individual para administradores e usuários (moradores/funcionários).
-   *Gerenciamento Completo de Encomendas:* Cadastro, edição, consulta e exclusão de encomendas.
-   *Associação Inteligente de Compartimentos:* Vincula encomendas a compartimentos (escaninhos/armários) disponíveis.
-   *Notificações Automatizadas por E-mail:*
    -   Confirmação ao destinatário quando uma encomenda é depositada.
    -   Alerta quando o prazo de retirada está se aproximando (configurável).
    -   Confirmação quando a encomenda é efetivamente retirada.
-   *Recuperação de Senha:* Mecanismo seguro para usuários redefinirem suas senhas.
-   *Histórico e Auditoria:* Registros detalhados de todas as movimentações de encomendas.
-   *Visualização da Taxa de Ocupação:* Painel para administradores monitorarem a disponibilidade dos compartimentos.

---

## 🧰 Tecnologias Utilizadas

-   *Backend:* Java 17+ e Spring Boot 3.x (para desenvolvimento ágil e robusto de APIs RESTful)
-   *Build Tool:* Maven (para gerenciamento de dependências e ciclo de vida do projeto)
-   *Conteinerização:* Docker & Docker Compose (para consistência de ambiente e facilidade de deploy)
-   *Banco de Dados:* PostgreSQL (sistema de gerenciamento de banco de dados relacional poderoso e open-source)
-   *Documentação da API:* Swagger (OpenAPI) (para documentação interativa e clara dos endpoints)
-   *Controle de Versão:* Git (para versionamento de código e colaboração)

---

## 💻 Pré-Requisitos de Ambiente


Antes de executar o projeto, garanta que você tenha as seguintes ferramentas instaladas e configuradas em seu ambiente de desenvolvimento:

-   [Java Development Kit (JDK) 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
-   [Apache Maven 3.6+](https://maven.apache.org/install.html)
-   [Docker Engine](https://www.docker.com/get-started)
-   [Docker Compose](https://docs.docker.com/compose/install/)
-   [Git](https://git-scm.com/downloads)

---

## 🛠️ Passo a Passo para Execução do MVP

### 1. Clone o Repositório

bash
git clone https://github.com/Gustavo-Jaccoud/Squad-16-TokSeg.git
cd Squad-16-TokSeg


### 2. Configure as Variáveis de Ambiente para E-mail (Obrigatório)


No arquivo `src/main/resources/application.properties`, preencha as propriedades de e-mail SMTP:

```properties
spring.mail.host=smtp.seuprovedor.com
spring.mail.port=587
spring.mail.username=seu-usuario
spring.mail.password=sua-senha
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```
> ⚠️ *Importante:* Nunca versionar esse arquivo com suas credenciais reais. Utilize variáveis de ambiente ou ferramentas de secrets em produção.


### 3. Build e Deploy com Docker Compose

```bash
./mvnw clean install

docker-compose up --build
```

> Este comando irá:
>-   Construir a imagem Docker da aplicação Java (se ainda não existir ou se houverem mudanças).
>-   Baixar e iniciar o container do banco de dados PostgreSQL.
>-   Iniciar o container da aplicação Tokseg Storage, que se conectará ao banco de dados.
>-   Expor a aplicação na porta 8081 do seu host.

### 3. Acesse a aplicação

Abra o navegador e acesse:


http://localhost:8081


A documentação da API (Swagger) estará em:


http://localhost:8081/swagger-ui/index.html


---

## 📁 Estrutura do Projeto
```markdown
Squad-16-TokSeg/
├── .mvn/                     # Wrapper do Maven
├── src/                      # Código-fonte da aplicação
│   ├── main/
│   │   ├── java/             # Código Java principal
│   │   └── resources/        # Arquivos de configuração (application.properties, etc.)
│   └── test/                 # Testes unitários e de integração
├── Dockerfile                # Instruções para construir a imagem Docker da aplicação
├── docker-compose.yml        # Define os serviços, redes e volumes com Docker Compose
├── pom.xml                   # Project Object Model - Configurações do Maven
├── README.md                 # Documentação do projeto
├── .gitignore                # Arquivos e diretórios ignorados pelo Git
├── mvnw                      # Script Maven Wrapper para Linux/macOS
└── mvnw.cmd                  # Script Maven Wrapper para Windows

```


----------

## 📌 Fluxos Principais

### 📥 Cadastro de Encomenda

1. O administrador cadastra uma nova encomenda para o morador.
2. O sistema associa a encomenda a um compartimento disponível.
3. O status do compartimento é alterado para “ocupado”.
4. O morador recebe um e-mail com a notificação.

### 📤 Retirada de Encomenda

1. O morador acessa o sistema com suas credenciais.
2. Informa que retirou a encomenda.
3. O status do compartimento volta para “livre”.
4. Registro é feito no histórico.

---

## 🛠️ Manutenção e Desenvolvimento (Execução Local sem Docker)

Se preferir rodar a aplicação diretamente na sua máquina (sem Docker):

1.  *Configure o Banco de Dados:*  Certifique-se de ter uma instância do PostgreSQL rodando e acessível.
2. *Configure o Java* : 
Garanta que o *JDK 17 ou superior* esteja instalado e configurado corretamente em sua máquina. Para verificar a versão instalada, execute o seguinte comando no terminal:
    ```bash
    java -version
    ```
	>Se o Java estiver instalado corretamente, o terminal deve exibir uma versão igual ou superior a 17.
	
	Além disso, verifique se o arquivo pom.xml do projeto está configurado com a mesma versão do JDK que está instalada em sua máquina. Para isso, edite ou valide a seguinte propriedade:
	```xml
	<properties>
	    <java.version>17</java.version>
	</properties>
	```
3.  *Configure as Propriedades da Aplicação:*
    
	Edite o arquivo `src/main/resources/application.properties`.
    - Ajuste as configurações de conexão com o banco de dados:
      ```properties
      spring.datasource.url=url-banco
      spring.datasource.username=usuario-banco
      spring.datasource.password=senha-banco
      ```
    - *Configure as propriedades de e-mail* conforme detalhado na seção 2. Configure as Variáveis de Ambiente para E-mail (Obrigatório).
5.  *Build da Aplicação (Local):*
       ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
      ```
---

## 🚀 Versão em Produção

A aplicação está disponível publicamente na plataforma Render:

🔗 **API Base:** [https://squad-16-tokseg.onrender.com](https://squad-16-tokseg.onrender.com)  
📘 **Documentação (Swagger):** [https://squad-16-tokseg.onrender.com/swagger-ui/index.html](https://squad-16-tokseg.onrender.com/swagger-ui/index.html)

> A URL da API pode ser usada por sistemas clientes para consumo dos endpoints.  
> A documentação Swagger exibe todas as rotas disponíveis, seus métodos, parâmetros e descrições.

---

## 🤝 Contribuição

Se desejar contribuir:

1. Fork este repositório.
2. Crie sua branch: git checkout -b minha-feature
3. Faça commit das suas alterações: git commit -m 'Minha nova feature'
4. Push para a branch: git push origin minha-feature
5. Abra um Pull Request.

---

## 📬 Suporte

Caso tenha problemas ou sugestões:

- Crie uma issue no GitHub
- Email: [gustavo.caua@souunit.com.br]

---
