# Usamos la imagen base de Maven para construir el proyecto
FROM maven:3.8.5-openjdk-17 AS build

# Configuramos el directorio de trabajo dentro del contenedor
WORKDIR /ricardo-spring

# Copiamos el archivo pom.xml y las dependencias del proyecto
COPY pom.xml .

# Descargamos las dependencias del proyecto
RUN mvn dependency:go-offline -B

# Copiamos el resto del proyecto
COPY src ./src

# Construimos el proyecto
RUN mvn clean package -DskipTests

# Usamos una imagen base de JDK para correr la aplicación
FROM openjdk:17-jdk-slim

# Configuramos el directorio de trabajo dentro del contenedor
WORKDIR /ricardo-spring

# Copiamos el archivo JAR generado desde la etapa de build
COPY --from=build /ricardo-spring/target/ricardo-spring-0.0.1-SNAPSHOT.jar app.jar

# Crea el directorio para los archivos PDF
RUN mkdir -p /app/pdfs-latex

# Exponemos el puerto en el que corre la aplicación
EXPOSE 8080

# Configuramos el comando para correr la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]