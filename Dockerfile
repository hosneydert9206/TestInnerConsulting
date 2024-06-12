# Usar una imagen base de JDK 11
FROM openjdk:11-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado al directorio de trabajo del contenedor
COPY target/producto-service-0.0.1-SNAPSHOT.jar /app/producto-service.jar

# Exponer el puerto que la aplicación usará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/producto-service.jar"]

