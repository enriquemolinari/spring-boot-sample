# SpringBoot para principiantes

Este es un proyecto de ejemplo de SpringBoot. Fue creado de la siguiente forma:

1. Ingresar en https://start.spring.io/
2. Elegir versiones, Maven y las dependencias: Spring Web, Spring Data, H2 Database, DevTools
3. Descargar el zip y descomprimirlo.
4. En Eclipse luego importar como "Existing Maven Project".

# CheatSheet

## Controllers

- Los controllers deben estar en un subpaquete del paquete donde esta el Main.
- @RestController para anotar los controladores.
- @RequestMapping(method = RequestMethod.GET,  path = "/hello") para anotar los métodos dentro de los controllers.
- o mejor @GetMapping(path = "/hello") directamente.
- Path parámeters: @GetMapping(path = "/path/{name}") y declara name como parametro: @PathVariable String name.
- @PostMapping, como parametro del método usar @RequestBody UnRecord nombreRecord

### Validations with Jackarta Bean Validation annotations
- https://beanvalidation.org/
- Controllers methods must include the @Valid annotation.
- Parameters of controller methods can incorporate standard bean annotations: @Size, @Future, etc. See app.users.UserRequest
- In order to respond custom messages to validation errores see class MyResponseEntityExceptionHandler.
 
## Exceptions
- Si quiero generar un handler generico para todos mis métodos de controller revisar la clase MyResponseEntityExceptionHandler.

## Dependency Injection

- @Component para anotar las clases que queremos que Spring cree las instancias (1 sola) y las injecte donde haga falta. Para que Spring injecte, es necesario crear el constructor (como debe ser).
- @Bean se utiliza para anotar métodos de intancias de clases third-party, y poder inyectarlas en nuestras clases. O clases nuestras pero que para construirlas se requiere de otras, o sea la construccion es mas compleja.

## Logging
- En application.properties, agregar logging.level.org.springframework = debug (default es info)

# Swagger
- Incluyendo la siguiente dependencia, tenemos Swagger UI automaticamente en http://localhost:8080/swagger-ui/index.html.
```
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.0.0</version>
</dependency>
```

# H2
- http://localhost:8080/h2-console/
- Si ponemos en src/main/resources un archivo llamado data.sql, podemos agregar datos a la BD. 
- spring.jpa.defer-datasource-initialization = true es necesario porque sino quiere ejecutar el data.sql antes que la BD se haya creado.
- spring.jpa.show-sql=true

# Repositories
- Siempre tienen que ser interfaces que extienden de JpaRepository<Entity, Type of ID>.
- Page & Sort: Hay que pasarle al findAll una instancia de PageRequest y Sort que define de que forma queremos ordenar. Ver UserController.usersJpaPagingAndSort()
- Ademas del CRUD que viene gratis, se pueden construir métodos donde el nombre del método define el criterio de filtros y sort, por ejemplo:
```
List<User> findByName(String name);   //filtra por nombre

List<User> findByNameAndBirthDateOrderByName(String name, LocalDate birthDate); //filtra por nombre, fecha de nacimiento y ordena. Los nombres deben coincidir con lo declarado en la entidad.
  
@Query("SELECT u FROM users_detail u WHERE u.name LIKE %:name%")
List<User> findByNameLike(String name);  // Si quiero algo custom uso @Query
```

## Configuration
- En lugar de usar la configuración de beans con un archivo xml, es posible mediante la anotación **@Configuration** customizar la inyección de beans mediante código Java. En una aplicación SpringBoot, spring escanea buscando clases anotadas con @Component (o similar), para crear beans e inyectarlos donde crea conveniente. Pero lo hace desde el paquete donde se encuentre el método *main* y sus subpaquetes. Para agregar más paquetes necesitamos usar la anotación **@ComponentScan**, la cual recibe una colección de paquetes para escanear y buscar beans para intanciar.
- Utilizando **@ComponentScan** *includeFilters* podemos incorporar el escaneo de beans usando nuestras propias anotaciones u otros criterios.
- Tambien es posible extender de *BeanPostProcessor* para meterme dentro de la instanciacion de beans que hace spring y retornar otra cosa, por ejemplo, un Bean decorado. 