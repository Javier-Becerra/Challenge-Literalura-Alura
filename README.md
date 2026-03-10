# 📚 Challenge!!!... Literalura en Java+Spring Boost.

Aplicación de consola desarrollada en *Java + Spring Boot* que permite consultar libros desde la API pública Gutendex y almacenarlos en una base de datos local para realizar diferentes consultas sobre libros y autores.

Este proyecto fue desarrollado como parte del Challenge Literalura de Alura LATAM.

---

# 🚀 Funcionalidades.

* Gestión de libros.
* Buscar libro por título utilizando la *API Gutendex*.
* Guardar libros y autores en base de datos.
* Evitar registros duplicados.
* Listar libros registrados.
* Listar libros por idioma.
* Mostrar Top 10 libros más descargados.
* Gestión de autores.
* Listar autores registrados.
* Listar autores vivos en un año específico.
* Listar autores nacidos después de un año.
* Mostrar estadísticas de descargas de libros utilizando DoubleSummaryStatistics.
* Cantidad de libros registrados.
* Promedio de descargas.
* Máximo de descargas.
* Mínimo de descargas.

---

# 🗄 Base de datos

El proyecto utiliza PostgreSQL como sistema de gestión de base de datos, administrado mediante pgAdmin 4.

PostgreSQL permite almacenar de forma persistente la información obtenida desde la API Gutendex, incluyendo:

* Libros
* Autores
* Idiomas
* Número de descargas

La aplicación utiliza Spring Data JPA para gestionar automáticamente las tablas y relaciones entre entidades.

---

# 📋 Menú principal.

Al ejecutar el programa se muestra el siguiente menú:

```text
====== LITERALURA ======

1 - Buscar libro por título
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un año
5 - Listar libros por idioma

6 - Top 10 libros más descargados
7 - Estadísticas de descargas
8 - Autores nacidos después de un año

0 - Salir
```

---

# 🌐 API utilizada

La aplicación consume datos desde la API pública:

```
👉 https://gutendex.com/
```
Ejemplo de consulta:
```
https://gutendex.com/books/?search=don+quijote
```
Datos obtenidos de la API:

* Título del libro
* Autor
* Idiomas
* Número de descargas
* Año de nacimiento del autor
* Año de fallecimiento del autor

# ⚙️ Tecnologías utilizadas.

* Java 25.
* Spring Boot.
* Spring Data JPA.
* Consumo de API REST.
* Jackson.
* Base de datos.
* Streams y Lambdas.
* JPQL.

---

# ▶️ Cómo ejecutar el proyecto.

1. Clone el repositorio.

2. Abra el proyecto en **IntelliJ IDEA**.

3. Cree la base de datos.

- Abrir pgAdmin 4
- Conectarse al servidor PostgreSQL
- Ir a:
  
```
Servers → PostgreSQL → Databases
```

- Clic derecho en Databases
- Seleccionar Create → Database
  Crear la base de datos con el nombre:

```
literalura
```

- Guarda los cambios.

4. Configurar conexión en Spring Boot:

- Abre el archivo:
 
```
src/main/resources/application.properties
```

- Agregar la siguiente configuración:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=tu_password

spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.format-sql=true
```

- Modifica los valores segun tus credenciales.

```
spring.datasource.username=postgres
spring.datasource.password=tu_password
```

5. Agregar dependencia PostgreSQL.

- En el archivo pom.xml agregar:

```
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>
```
   
6. Ejeuta la aplicación.

- Inicia la aplicación:

```
LiteraluraApplication.java
```
---

# 🎯 Objetivos

Proyecto desarrollado como práctica de:

* Consumo de APIs REST
* Persistencia con JPA
* Streams y Lambdas
* Consultas con JPQL
* Arquitectura en capas
* Manejo de JSON con Jackson

En programa Oracle Nex Education - ALURA.

---

# 📄 Licencia

Este proyecto es de uso educativo y libre para aprendizaje.
