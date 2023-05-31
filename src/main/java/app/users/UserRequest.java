package app.users;

import jakarta.validation.constraints.Size;

public record UserRequest(@Size(min = 2, message = "name must not be less than 2 chars") String name,  String birthdate) {

}
