package app.users;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

  private static List<User> users =
      List.of(new User(1, "Enrique", LocalDate.now().minusYears(34)),
          new User(2, "Lucia", LocalDate.now().minusYears(13)), new User(3, "Nicolas",
              LocalDate.now().minusYears(10)));
  
  private static int usersCount = 3;
  
  public List<UserRecord> findAll() {
    return users.stream().map(u -> u.toRecord()).collect(Collectors.toUnmodifiableList());
  }


  public void createUser(String name, String birthdate) {
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");;
    
    users = new ArrayList<>(users);
    users.add(new User(++usersCount, name, LocalDate.parse(birthdate, formatter)));
    
  }
}
