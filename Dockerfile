# Usamos una imagen ligera de Java
FROM eclipse-temurin:25-jre-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo JAR generado por Gradle al contenedor
# Nota: Debes ejecutar 'gradlew build -x test' antes de hacer el docker-compose up
COPY build/libs/*-SNAPSHOT.jar app.jar

# Exponemos el puerto
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]