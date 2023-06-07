package app.users;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity(name = "users_detail")
public class User {

  @Id
  @GeneratedValue
  private Integer id;
  @Size(min = 2)
  private String name;
  private LocalDate birthDate;
  
  public User() {
    
  }
  
  public User(Integer id, String name, LocalDate birthDate) {
    this.id = id;
    this.name = name;
    this.birthDate = birthDate;
  }

  public UserRecord toRecord() {
    return new UserRecord(this.id.toString(), this.name, this.birthDate.toString());
  }
}
