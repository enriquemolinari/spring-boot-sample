package app.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import app.users.User;

public interface UserRepository extends JpaRepository<User, Integer>{
  
  List<User> findByName(String name);
  
  List<User> findByNameAndBirthDateOrderByName(String name, LocalDate birthDate);
  
  @Query("SELECT u FROM users_detail u WHERE u.name LIKE %:name%")
  List<User> findByNameLike(String name);
}
 