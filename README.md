# Manufacturing Management API 

Esta es una **API REST desarrollada con Spring Boot** diseñada para administrar usuarios, grupos de seguridad y niveles de acceso en un sistema de manufactura. El proyecto implementa un modelo de permisos granulares mediante una entidad intermedia.

##  Tecnologías Utilizadas

*   **Backend:** Java 17, Spring Boot 3.x
*   **Acceso a Datos:** Spring Data JPA, Hibernate
*   **Base de Datos:** MySQL (Modelo Relacional)
*   **Mapeo y Validación:** ModelMapper, Lombok
*   **Arquitectura:** Arquitectura por capas (Controller - Service - Repository)

##  Características Principales

### 1. Modelo de Seguridad Granular
El sistema utiliza una **relación ternaria** representada por la entidad `User_Security`. Esto permite:
*   Que un usuario pertenezca a múltiples grupos de seguridad.
*   Que un usuario tenga diferentes **Niveles de Acceso** (Read, Write, Admin, Owner) dependiendo del grupo al que pertenece.

### 2. Manejo Global de Excepciones
`@RestControllerAdvice` para centralizar el manejo de errores, garantizando respuestas JSON consistentes:
*   **404 Not Found:** Cuando el usuario, grupo o nivel de acceso no existen.
*   **400 Bad Request:** Para errores de validación (ej. contraseñas cortas) o lógica de negocio.
*   **409 Conflict:** Manejo de nombres de usuario duplicados mediante validaciones previas (`existsByUsername`).

---

##  Endpoints de la API

### Usuarios (`/api/users`)
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/users` | Lista la información básica de todos los usuarios. |
| `GET` | `/api/users/{username}` | Obtiene los detalles de un usuario específico por su alias. |
| `GET` | `/api/users/with-access` | Lista usuarios incluyendo sus grupos y permisos detallados. |
| `POST` | `/api/users` | Registra un nuevo usuario y le asigna un grupo/nivel inicial. |

### Grupos de Seguridad (`/api/groups`)
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/groups/{username}` | Lista todos los grupos de seguridad asignados a un usuario específico. |

---

### 📝 Notas de Desarrollo
*   El proyecto sigue las convenciones  **Conventional Commits**.
*   Se utilizan **DTOs** (Data Transfer Objects) para desacoplar el contrato de la API del esquema de la base de datos.
*   Las respuestas de error incluyen un `timestamp` automático generado en el servidor para facilitar el seguimiento de incidencias.

---