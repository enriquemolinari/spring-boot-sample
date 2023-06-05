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

## Services

- @Component para anotar las clases que queremos que Spring cree las instancias (1 sola) y las injecte donde haga falta.
- @Bean se utiliza para anotar métodos de intancias de clases third-party, y poder inyectarlas en nuestras clases.

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

