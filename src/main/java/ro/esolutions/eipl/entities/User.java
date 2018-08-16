package ro.esolutions.eipl.entities;

import lombok.*;
import ro.esolutions.eipl.models.UserRole;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private UserRole userRole;
    private Boolean isActive;
}
