package app.users;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
