# API de Gestión de Notas

## Descripción
Esta API REST proporciona una solución completa para la gestión de notas y usuarios. Permite crear, leer, actualizar y eliminar notas asociadas a usuarios, con validaciones robustas y manejo de errores personalizado.

## Tecnologías Utilizadas
- Java 17
- Spring Boot 3.5.0
- Spring Data JPA
- MySQL
- Maven
- Jakarta Validation
- Jackson para serialización JSON

## Estructura del Proyecto
```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── api/
│   │           └── api/
│   │               ├── controller/
│   │               │   ├── NotasController.java
│   │               │   └── UsuarioController.java
│   │               ├── model/
│   │               │   ├── Nota.java
│   │               │   └── Usuario.java
│   │               ├── repository/
│   │               │   ├── NotaRepository.java
│   │               │   └── UsuarioRepository.java
│   │               ├── service/
│   │               │   ├── NotaService.java
│   │               │   ├── NotaServiceImpl.java
│   │               │   ├── UsuarioService.java
│   │               │   └── UsuarioServiceImpl.java
│   │               └── exception/
│   │                   └── GlobalExceptionHandler.java
│   └── resources/
│       └── application.properties
```

## Modelos de Datos

### Usuario
```json
{
    "id": 1,
    "nombre": "Usuario Ejemplo",
    "email": "usuario@ejemplo.com",
    "passwordHash": "contraseña123"
}
```

### Nota
```json
{
    "id": 1,
    "titulo": "Mi Nota",
    "contenido": "Contenido de la nota",
    "fechaCreacion": "2024-03-20T10:00:00",
    "usuario": {
        "id": 1
    }
}
```

## Validaciones

### Usuario
- ID: Debe ser positivo
- Nombre: No puede estar vacío, entre 3 y 50 caracteres
- Email: Debe ser válido y único
- PasswordHash: No puede estar vacío

### Nota
- ID: Debe ser positivo
- Título: No puede estar vacío, entre 3 y 100 caracteres
- Contenido: No puede estar vacío, entre 5 y 1000 caracteres
- Usuario: Debe existir en la base de datos

## Manejo de Errores
La API utiliza un manejador global de excepciones que devuelve:
- Código 409 (Conflict) para errores de negocio
- Código 400 (Bad Request) para errores de validación
- Código 500 (Internal Server Error) para errores inesperados

## Configuración de Base de Datos
```properties
spring.datasource.url=jdbc:mysql://localhost:3307/Notasdb
spring.datasource.username=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

## Relaciones
- Un usuario puede tener múltiples notas (One-to-Many)
- Una nota pertenece a un usuario (Many-to-One)
- Las notas se eliminan en cascada cuando se elimina el usuario

## Características Adicionales
- Validación automática de datos
- Manejo de relaciones bidireccionales
- Serialización JSON personalizada
- Ordenamiento de resultados
- Filtrado por usuario
- Logging de operaciones

## Ejemplos de Uso
## Pruebas de Endpoints

A continuación se muestra una plantilla para probar todos los endpoints principales de la API usando curl. Puedes copiar y pegar cada bloque en tu terminal y modificar los valores según tus necesidades.

---

### Usuarios

#### Crear usuario
![Crear usuario](src/images/1%20post%20user.png)
```bash
curl -X POST http://localhost:8080/api/v1/Usuario \
-H "Content-Type: application/json" \
-d '{
  "nombre": "Nuevo Usuario",
  "email": "nuevo@email.com",
  "passwordHash": "contraseña123"
}'
```

#### Obtener todos los usuarios
![Obtener todos los usuarios](src/images/3%20getAll%20user.png)
```bash
curl -X GET http://localhost:8080/api/v1/Usuario
```

#### Obtener usuario por ID
![Obtener usuario por ID](src/images/get%20user%20by%20id.png)
```bash
curl -X GET http://localhost:8080/api/v1/Usuario/1
```

#### Actualizar usuario
![Actualizar usuario](src/images/4%20put%20user.png)
```bash
curl -X PUT http://localhost:8080/api/v1/Usuario/1 \
-H "Content-Type: application/json" \
-d '{
  "nombre": "Usuario Actualizado",
  "email": "actualizado@email.com",
  "passwordHash": "nuevaContraseña"
}'
```

#### Eliminar usuario
![Eliminar usuario](src/images/5%20delete%20user.png)
```bash
curl -X DELETE http://localhost:8080/api/v1/Usuario/1
```

---

### Notas

#### Crear nota
![Crear nota](src/images/2%20post%20note.png)
```bash
curl -X POST http://localhost:8080/api/v1/notas \
-H "Content-Type: application/json" \
-d '{
  "titulo": "Nueva Nota",
  "contenido": "Contenido de la nota",
  "usuario": {
    "id": 1
  }
}'
```

#### Obtener todas las notas
![Obtener todas las notas](src/images/get%20all%20notes.png)
```bash
curl -X GET http://localhost:8080/api/v1/notas
```

#### Buscar notas de un usuario (filtrado)
![Obtener notas de un usuario](src/images/get%20note%20by%20user%20id.png)
```bash
curl -X GET "http://localhost:8080/api/v1/notas?usuarioId=1"
```

#### Obtener nota por ID
![Obtener nota por ID](src/images/get%20note%20by%20id.png)
```bash
curl -X GET http://localhost:8080/api/v1/notas/1
```

#### Buscar notas por título, contenido o ID
![Buscar notas por título o contenido](src/images/6%20search%20note.png)
```bash
# Buscar por título
curl -X GET "http://localhost:8080/api/v1/notas/search?titulo=importante"

# Buscar por contenido
curl -X GET "http://localhost:8080/api/v1/notas/search?contenido=tarea"

# Buscar por ID
curl -X GET "http://localhost:8080/api/v1/notas/search?id=1"

# Buscar por título y contenido (cualquiera de los dos que coincida)
curl -X GET "http://localhost:8080/api/v1/notas/search?titulo=importante&contenido=tarea"

# Buscar y ordenar por fechaCreacion descendente
curl -X GET "http://localhost:8080/api/v1/notas/search?titulo=importante&sortBy=fechaCreacion&order=desc"
```


## Consideraciones de Seguridad
- Validación de datos de entrada
- Manejo seguro de contraseñas
- Prevención de inyección SQL
- Validación de IDs positivos
- Manejo de relaciones seguras

## Mejores Prácticas Implementadas
- Separación de responsabilidades
- Inyección de dependencias
- Manejo de excepciones centralizado
- Validaciones robustas
- Documentación clara
- Código limpio y mantenible

## Requisitos del Sistema
- Java 17 o superior
- MySQL 8.0 o superior
- Maven 3.6 o superior
- 2GB de RAM mínimo
- 1GB de espacio en disco

## Instalación y Ejecución
1. Clonar el repositorio
2. Configurar la base de datos MySQL
3. Modificar application.properties si es necesario
4. Ejecutar `mvn spring-boot:run`
5. La API estará disponible en `http://localhost:8080`

## Contribución
1. Fork el repositorio
2. Crear una rama para tu feature
3. Commit tus cambios
4. Push a la rama
5. Crear un Pull Request

## Licencia
Este proyecto está bajo la Licencia MIT.

## Contacto
Para soporte o consultas, por favor abrir un issue en el repositorio. 