package ro.esolutions.eipl.entities;

import lombok.*;
import ro.esolutions.eipl.models.UserRole;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserEntity {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private UserRole userRole;
    private Boolean isActive;
}
