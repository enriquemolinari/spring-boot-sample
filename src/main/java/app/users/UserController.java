package app.users;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import app.repositories.UserRepository;
import jakarta.validation.Valid;

@RestController
public class UserController {

   private UserDao users;
   private UserRepository repo;
   
   public UserController(UserDao users, UserRepository repo) {
     this.users = users;
     this.repo = repo;
   }

   @GetMapping("/users2")
   public List<UserRecord> users2() {
     throw new RuntimeException("This is to test the global exception handler");
   }

   
   @GetMapping("/users")
   public List<UserRecord> users() {
     return this.users.findAll();
   }
  
   @PostMapping("/users")
   public void newUser(@Valid @RequestBody UserRequest user) {
     this.users.createUser(user.name(), user.birthdate()); 
   }

   private String pepe() {
     return "aaa";
   }
   
   private Long pepe2() {
     return 1L;
   }
   
   @GetMapping("/sin")
   public String pruebaSin(@RequestParam(required = false) String token) {   
    
     var valor = ifAuthenticatedDo(token, (String id) -> {return pepe();});
     
     return valor;
   }
   
   @GetMapping("/sin2")
   public Long pruebaSin2(@RequestParam(required = false) String token) {   
    
     var valor = ifAuthenticatedDo(token, (String id) -> {return pepe2();});
     
     return valor;
   }
   
   private <T,S> S ifAuthenticatedDo(String token, Function<String, S> s) {
     
     String val = Optional.ofNullable(token).map(a -> {
       return a;
     }).orElseThrow(() -> new RuntimeException("sin cookie"));
     
     return s.apply(val);
    //return null;
  }

  @GetMapping("/pruebacookie")
   public void pruebaCookies(@CookieValue(required = false) String bla) {
     Optional.ofNullable(bla).orElseThrow(() -> new RuntimeException("sin cookie"));
    
     
     System.out.println(bla); 
   }
   
   @GetMapping("/jpa/users/paging")
   public List<UserRecord> usersJpaPagingAndSort() {
     var s = Sort.by(Sort.Direction.DESC, "id");
     return repo.findAll(PageRequest.of(0, 2, s)).stream().map(u -> u.toRecord()).collect(Collectors.toUnmodifiableList());
   }
   
   @GetMapping("/jpa/users")
   public List<UserRecord> usersJpa() {
     return repo.findAll().stream().map(u -> u.toRecord()).collect(Collectors.toUnmodifiableList());
   }

   @GetMapping("/jpa/users/{name}")
   public List<UserRecord> usersByName(@PathVariable String name) {
     return repo.findByName(name).stream().map(u -> u.toRecord()).collect(Collectors.toUnmodifiableList());
   }

   @GetMapping("/jpa/users/query/{name}")
   public List<UserRecord> usersByQuery(@PathVariable String name) {
     return repo.findByNameLike(name).stream().map(u -> u.toRecord()).collect(Collectors.toUnmodifiableList());
   }
   
}
