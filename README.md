# Banco Sistema MVP - Backend API

Esta es la capa de la API RESTful del **Sistema Bancario MVP**, desarrollada con **Spring Boot (Java)** y orientada a microservicios. Expone los endpoints necesarios para la gestión transaccional de **Cuentas, Clientes y Movimientos** utilizando como motor de base de datos **PostgreSQL**.

## 🚀 Arquitectura y Tecnologías
- **Framework Core:** Spring Boot 3 / Java
- **Base de Datos:** PostgreSQL 17
- **ORM:** Hibernate / Spring Data JPA
- **Contenedores:** Docker & Docker Compose
- **Documentación de API:** Swagger (OpenAPI 3)

---

## 🛠 Entorno de Ejecución (Desarrollo y Producción)

El proyecto está diseñado para funcionar en aislamiento gracias a **Docker**, el cual orquesta no solo el aplicativo Java, sino su base de datos y script inicial (`bd.sql`).

### Opción 1: Ejecución completa con Docker (Recomendado)

Asegúrate de tener instalado [Docker](https://www.docker.com/) en tu máquina.

1. Sitúate en la raíz del repositorio `backend-api`.
2. **Crea tu archivo de variables de entorno:**
   Copia el archivo de plantilla y configura tus credenciales (especialmente las contraseñas):
   ```bash
   cp .env.template .env
   ```
3. Levanta la infraestructura en segundo plano ejecutando:
   ```bash
   docker-compose up --build -d
   ```
3. Docker inicializará la red virtual, construirá la imagen del backend, y levantará Postgres instanciando la estructura y datos de la base de datos automáticamente desde `database/init/bd.sql`.
4. La API RESTful quedará expuesta en `http://localhost:8080`.

Para detener los servicios, usa:
```bash
docker-compose down
```

### Opción 2: Ejecución Local de Spring Boot nativo

Si prefieres levantar el servidor Java nativamente a través de tu IDE o Gradle, primero debes garantizar que exista una instancia de PostgreSQL en ejecución.

1. Levanta **exclusivamente** la base de datos mediante Docker Compose (o configura tu propio servidor en el puerto local 5434 o el especificado en `application.properties`):
   ```bash
   docker-compose up db_banco -d
   ```
2. Compila e inicializa Spring Boot mediante el _wrapper_ de Gradle local:
   ```bash
   # En Linux/Mac
   ./gradlew bootRun
   
   # En Windows
   .\gradlew bootRun
   ```

---

## 📚 Documentación de Puertos y Endpoints

Una vez la aplicación esté en estado "_Running_", puedes inspeccionar e interactuar visualmente con todos los controladores HTTP habilitados usando la interfaz asíncrona de **Swagger UI**:

- **Acceso a la Documentación (Navegador):** `http://localhost:8080/swagger-ui.html`

## 🧪 Pruebas Unitarias

La lógica del negocio centraliza su fiabilidad mediante pruebas unitarias escritas en `JUnit 5` y `Mockito`. 
Para ejecutar el árbol de test completo escribe en terminal:

```bash
# Linux/Mac
./gradlew test

# Windows
.\gradlew test
```

## 🧪 Pruebas con Postman

Para facilitar las pruebas de integración y flujos completos, se ha incluido una colección de Postman en el repositorio.

- **Ubicación:** `docs/postman/TCS reto.postman_collection.json`
- **Instrucciones:** Importa el archivo JSON en tu cliente de Postman para acceder a todos los endpoints pre-configurados.
