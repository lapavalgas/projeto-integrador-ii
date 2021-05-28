# Fast Service

Esse é um produto da disciplina de Projeto Integrador II do curso de Análise e Desenvolvimento de Sistemas (Centro Universitário de São José, São José, Santa Catarina, Brasil). O objetivo nessa disciplina é desenvolver um MVP (Minimum Viable Product). O propósito dessa aplicação (fastservice) é funcionar como um hub para oferecer e solicitar serviços, basicamente um marketplace de serviços.

A API foi desenvolvida utilizando MySQL, Java e Spring Boot. Existe um conjunto de 26 rotas que possibilitam operar a aplicação. A interface frontend que consome essa API foi desenvolvida utilizando o framework Vue.js. E a infraestrutura desse projeto é garantida através do docker.

## Como rodar?
1. Instalar o Git
2. Instalar o Docker 
3. Executar os seguintes comandos no Windows

```
1. git clone https://github.com/lapavalgas/projeto-integrador-ii-fastservice

2. cd projeto-integrador-ii-fastservice

3. start cmd.exe /c docker-compose up

4. start "" "http://localhost:8082/"
```