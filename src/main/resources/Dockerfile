# Imagem de Origem
FROM maven:3.6.3-openjdk-11-slim

# diretório de trabalho no interior do container
WORKDIR /backend

# copy packages from root to container folders
COPY ./ /backend

# running spring on start
CMD ["mvn", "spring-boot:run"]