# Etapa 1: Build com Maven
FROM maven:3.9.6-eclipse-temurin-23-alpine AS builder
WORKDIR /app

# Copia todos os arquivos do projeto
COPY . .

# Compila o projeto e gera o .jar
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final
FROM eclipse-temurin:23-jdk-alpine
WORKDIR /app

# Copia o .jar gerado na etapa de build
COPY --from=builder /app/target/storage-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
