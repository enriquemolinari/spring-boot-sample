# SpringBoot para principiantes

Este es un proyecto de ejemplo de SpringBoot. Fue creado de la siguiente forma:

1. Ingresar en https://start.spring.io/
2. Elegir versiones, Maven y las dependencias: Spring Web, Spring Data, H2 Database, DevTools
3. Descargar el zip y descomprimirlo.
4. En Eclipse luego importar como "Existing Maven Project".

# CheatSheet

- Los controllers deben estar en un subpaquete del paquete donde esta el Main.
- @RestController para anotar los controladores.
- @RequestMapping(method = RequestMethod.GET,  path = "/hello") para anotar los métodos dentro de los controllers.
- o mejor @GetMapping(path = "/hello") directamente.
