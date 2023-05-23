package app.users;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

   private UserDao users;
   
   public UserController(UserDao users) {
     this.users = users;
   }
   
   @GetMapping("/users")
   public List<UserRecord> users() {
     return this.users.findAll();
   }
  
   @PostMapping("/users")
   public void newUser(@RequestBody UserRequest user) {
     this.users.createUser(user.name(), user.birthdate()); 
   }
}
