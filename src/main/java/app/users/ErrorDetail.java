package app.users;

import java.time.LocalDateTime;

public record ErrorDetail(LocalDateTime timestamp, String message) {
  
}