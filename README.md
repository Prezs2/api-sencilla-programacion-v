# API Sencilla Programación V

API REST desarrollada con **Spring Boot** para gestionar estudiantes y programas académicos.

El proyecto utiliza **Java 21**, **Spring Data JPA**, **SQLite**, **Hibernate**, **Bean Validation** y **Springdoc OpenAPI / Swagger**.

---

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- SQLite
- Lombok
- Jakarta Validation
- Springdoc OpenAPI / Swagger
- Maven

---

## Objetivo del proyecto

El objetivo de esta aplicación es practicar la construcción de una API REST con Spring Boot aplicando separación por capas, DTOs con `record`, persistencia con JPA y validaciones.

Actualmente permite trabajar con:

- Programas académicos
- Estudiantes
- Relaciones entre estudiantes y programas académicos
- Operaciones CRUD
- Validación de datos de entrada
- Persistencia en SQLite
- Documentación y pruebas de endpoints con Swagger

---

## Estructura del proyecto

```text
src/main/java/com/spring/activity
│
├── config
│   └── DataInitializer.java
│
├── controller
│   ├── ProgramaAcademicoController.java
│   └── ...
│
├── domain
│   ├── estudiante
│   │   ├── EstudianteRequest.java
│   │   └── EstudianteResponse.java
│   │
│   └── programaacademico
│       ├── ProgramaAcademicoRequest.java
│       └── ProgramaAcademicoResponse.java
│
├── entity
│   ├── Estudiante.java
│   └── ProgramaAcademico.java
│
├── repository
│   ├── EstudianteRepository.java
│   └── ProgramaAcademicoRepository.java
│
├── service
│   ├── EstudianteService.java
│   └── ProgramaAcademicoService.java
│
└── ApiSencillaProgramacionVApplication.java
```

---

## Arquitectura

El proyecto utiliza una arquitectura por capas:

```text
Cliente
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
Base de datos
```

### Controller

Recibe las peticiones HTTP y devuelve las respuestas correspondientes.

### Service

Contiene la lógica de negocio de la aplicación.

### Repository

Se encarga del acceso a datos mediante Spring Data JPA.

### Entity

Representa las tablas almacenadas en SQLite.

### DTO

Los objetos de entrada y salida se implementan utilizando `record`.

Ejemplo:

```java
public record ProgramaAcademicoRequest(
        String nombre,
        String codigo
) {
}
```

```java
public record ProgramaAcademicoResponse(
        Long id,
        String nombre,
        String codigo
) {
}
```

Esto permite separar las entidades JPA de los datos expuestos por la API.

---

## Relación entre entidades

Un estudiante pertenece a un programa académico.

Conceptualmente:

```text
Programa Académico
      │
      ├── Estudiante
      ├── Estudiante
      └── Estudiante
```

La relación se modela con JPA mediante `@ManyToOne`.

Ejemplo:

```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "programa_academico_id")
private ProgramaAcademico programaAcademico;
```

---

## Base de datos

El proyecto utiliza **SQLite**.

Ejemplo de configuración:

```properties
spring.datasource.url=jdbc:sqlite:database.db
spring.datasource.driver-class-name=org.sqlite.JDBC

spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

La base de datos se almacena en el archivo:

```text
database.db
```

---

## Datos iniciales

El proyecto utiliza un `CommandLineRunner` para cargar datos iniciales cuando la aplicación inicia.

Ejemplo:

```java
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(...) {
        return args -> {
            // Datos iniciales
        };
    }
}
```

Actualmente se pueden cargar programas académicos como:

```text
SIS - Ingeniería de Sistemas
ADM - Administración de Empresas
```

y estudiantes asociados a estos programas.

---

## API de programas académicos

Ruta base:

```text
/v1/programas-academicos
```

Endpoints recomendados:

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/v1/programas-academicos` | Crear programa académico |
| GET | `/v1/programas-academicos` | Listar programas académicos |
| GET | `/v1/programas-academicos/{id}` | Buscar programa por ID |
| PUT | `/v1/programas-academicos/{id}` | Actualizar programa |
| DELETE | `/v1/programas-academicos/{id}` | Eliminar programa |

### Crear un programa académico

Request:

```json
{
  "nombre": "Ingeniería de Sistemas",
  "codigo": "SIS"
}
```

Response:

```json
{
  "id": 1,
  "nombre": "Ingeniería de Sistemas",
  "codigo": "SIS"
}
```

---

## Estudiantes

Ejemplo de request:

```json
{
  "documento": "1001001001",
  "nombre": "Ana Torres",
  "correo": "anaTorres@universidad.edu.co",
  "programaAcademicoId": 1
}
```

Los datos del estudiante se validan antes de llegar a la capa de servicio.

Ejemplo de DTO:

```java
public record EstudianteRequest(

        @NotBlank(message = "El documento es obligatorio")
        @Size(min = 6, max = 20)
        @Pattern(regexp = "\\d+")
        String documento,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 100)
        String nombre,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo no tiene un formato válido")
        String correo,

        @NotNull(message = "El programa académico es obligatorio")
        @Positive
        Long programaAcademicoId

) {
}
```

---

## Swagger / OpenAPI

La documentación de la API puede consultarse mediante Swagger UI.

Si el proyecto mantiene esta configuración:

```properties
springdoc.swagger-ui.path=/swagger-ui-custom.html
```

la interfaz estará disponible en:

```text
http://localhost:8080/swagger-ui-custom.html
```

La especificación OpenAPI también se encuentra disponible en:

```text
http://localhost:8080/v3/api-docs
```

---

## Cómo ejecutar el proyecto

### Requisitos

Debes tener instalado:

- Java 21
- Maven
- Git

Verifica Java:

```bash
java -version
```

Verifica Maven:

```bash
mvn -version
```

### Clonar el repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
```

Entrar al proyecto:

```bash
cd api-sencilla-programacion-v
```

### Compilar

```bash
mvn clean install
```

### Ejecutar

```bash
mvn spring-boot:run
```

También puedes ejecutar directamente la clase:

```text
ApiSencillaProgramacionVApplication
```

desde IntelliJ IDEA.

---

## Buenas prácticas aplicadas

El proyecto busca aplicar buenas prácticas como:

- Separación entre Controller, Service y Repository
- Uso de DTOs para no exponer directamente las entidades
- DTOs implementados con Java `record`
- Inyección de dependencias por constructor
- Validación con Jakarta Validation
- Uso de códigos HTTP apropiados
- Relaciones JPA entre entidades
- Nombres de variables siguiendo `camelCase`
- Persistencia desacoplada mediante repositories
- Documentación de API con OpenAPI

---

## Autor

Proyecto desarrollado con fines educativos para practicar **Java moderno y Spring Boot**.
