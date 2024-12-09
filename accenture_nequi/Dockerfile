FROM ubuntu:latest
LABEL authors="kevin"

ENTRYPOINT ["top", "-b"]

FROM openjdk:21-jdk-slim as builder

WORKDIR /app

# Copiar el archivo pom.xml para descargar las dependencias
COPY pom.xml .

# Usar Maven para descargar las dependencias sin construir el proyecto todavía
RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline

# Copiar el código fuente
COPY src ./src

# Empaquetar el proyecto con Maven
RUN mvn clean package -DskipTests

# Usar una imagen base de Tomcat para la ejecución
FROM tomcat:10-jdk21-slim

# Establecer directorio de trabajo en Tomcat
WORKDIR /usr/local/tomcat/webapps

# Copiar el archivo WAR desde la etapa de construcción a la carpeta webapps de Tomcat
COPY --from=builder /app/target/accenture_nequi-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/accenture_nequi-0.0.1-SNAPSHOT.war

# Exponer el puerto en el que Tomcat estará escuchando (por defecto es 8080)
EXPOSE 8080

# Comando para ejecutar Tomcat
CMD ["catalina.sh", "run"]
