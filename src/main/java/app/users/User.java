package app.users;

import java.time.LocalDate;
import jakarta.validation.constraints.Size;

public class User {

  private Integer id;
  @Size(min = 2)
  private String name;
  private LocalDate birthDate;
  
  public User(Integer id, String name, LocalDate birthDate) {
    this.id = id;
    this.name = name;
    this.birthDate = birthDate;
  }

  public UserRecord toRecord() {
    return new UserRecord(this.id.toString(), this.name, this.birthDate.toString());
  }
}
