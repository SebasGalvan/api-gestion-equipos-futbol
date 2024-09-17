# API de Gestión de Equipos de Fútbol

## Descripción
Esta aplicación es una API RESTful desarrollada utilizando **Spring Boot 3** y **Java 17**. 

Su propósito es gestionar información sobre equipos de fútbol, permitiendo realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre los equipos.

---
En root del proyecto se encuentra una carpeta `coleccion_postman` con la colección para realizar pruebas en esta herramienta. 

Alternativa: al levantar el proyecto si acceden a `http://localhost:8080/docs` tendrán disponible un swagger con los mismos endpoints.

---

## Docker

Pasos para construir imagen e iniciar contenedor en docker:


### 1. Empaquetar la aplicaion
  ```bash
    mvn package
  ```
### 2. Construir imagen
  ```bash
    docker compose build
  ```
### 2. Levantar el contenedor con la imagen
  ```bash
    docker compose up
  ```

## Informe de cobertura

Pasos para obtener y ver el reporte de cobertura de test:


### 1. Ejecutar los test y generacion de reporte
  ```bash
    mvn clean test site
  ```
En la carpeta `\target\site\jacoco\` se encontrara el archivo `index.html` con el informe de combertura de los servicios. 



## Funcionalidades
La API proporciona las siguientes funcionalidades:

### 1. Consulta de Todos los Equipos
- **Endpoint:** `GET /equipos`
- **Descripción:** Devuelve la lista de todos los equipos de fútbol registrados.
- **Respuesta Exitosa (200 OK):**
  ```json
  [
    {
      "id": 2,
      "nombre": "FC Barcelona",
      "liga": "La Liga",
      "pais": "España"
    },
    ...
  ]
  ```

### 2. Consulta de un Equipo por ID
- **Endpoint:** `GET /equipos/{id}`
- **Descripción:** Devuelve la información del equipo correspondiente al ID proporcionado.
- **Respuesta Exitosa (200 OK):**
  ```json
  {
    "id": 2,
    "nombre": "Barcelona FC",
    "liga": "La Liga",
    "pais": "España"
  }
  ```

### 3. Búsqueda de Equipos por Nombre
- **Endpoint:** `GET /equipos/buscar?nombre={valor}`
- **Descripción:** Devuelve la lista de equipos cuyos nombres contienen el valor proporcionado en el parámetro de búsqueda.
- **Respuesta Exitosa (200 OK):**
  ```json
  [
    {
      "id": 1,
      "nombre": "Real Madrid",
      "liga": "La Liga",
      "pais": "España"
    },
    ...
  ]
  ```

### 4. Creación de un Nuevo Equipo
- **Endpoint:** `POST /equipos`
- **Descripción:** Permite crear un nuevo equipo.
- **Cuerpo de la Petición:**
  ```json
  {
    "nombre": "Nuevo Equipo FC",
    "liga": "Nueva Liga",
    "pais": "Nuevo País"
  }
  ```
- **Respuesta Exitosa (201 Created):**
  ```json
  {
    "id": 26,
    "nombre": "Nuevo Equipo FC",
    "liga": "Nueva Liga",
    "pais": "Nuevo País"
  }
  ```

### 5. Actualización de Información de un Equipo
- **Endpoint:** `PUT /equipos/{id}`
- **Descripción:** Actualiza la información de un equipo existente.
- **Cuerpo de la Petición:**
  ```json
  {
    "nombre": "Nuevo Nombre",
    "liga": "Nueva Liga",
    "pais": "Nuevo País"
  }
  ```
- **Respuesta Exitosa (200 OK):**
  ```json
  {
    "id": 1,
    "nombre": "Nuevo Nombre",
    "liga": "Nueva Liga"
  }
  ```

### 6. Eliminación de un Equipo
- **Endpoint:** `DELETE /equipos/{id}`
- **Descripción:** Elimina un equipo existente.
- **Respuesta Exitosa (204 No Content):** Sin contenido.

## Manejo de Errores
La API implementa manejo de excepciones, devolviendo códigos de estado HTTP apropiados:
- **401 Unauthorized:** Si la autenticación falla.
- **404 Not Found:** Si no encuentra el recurso solicitado.
- **400 Bad Request:** Si la solicitud no es correcta.

## Seguridad
La API utiliza **JWT** (Sprig Security) para la autenticación. 

Se debe enviar una petición  `POST` a `/auth/login` con el siguiente cuerpo para autenticarse:
```json
{
  "username": "test",
  "password": "12345"
}
```

## Stack
- Java 17
- Spring Boot 3
- Spring JPA
- Spring Security
- Springdoc - Open API
- Base de datos H2 - en memoria
- Docker (opcional para la dockerización)

## Instalación
1. Clona el repositorio.
2. Configura el entorno de desarrollo, IDE de preferencia.
3. Ejecuta la aplicación.
"# api-gestion-equipos-futbol" 
"# api-gestion-equipos-futbol" 
