package ro.esolutions.eipl.models;

import lombok.*;
import ro.esolutions.eipl.models.constraints.UniqueEmail;
import ro.esolutions.eipl.models.constraints.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserModelWithPassword {
    private Long id;

    @UniqueUsername
    @NotEmpty
    @Size(min = 2)
    private String username;

    @NotEmpty
    @Size(min = 2)
    private String firstName;

    @NotEmpty
    @Size(min = 2)
    private String lastName;

    @NotNull
    private UserRole userRole;

    @NotNull
    private Boolean isActive;

    @NotNull
    @Size(min = 5)
    @UniqueEmail(message = "Email ${validatedValue} already exists")
    @Email
    private String email;

    @NotNull
    @Size(min = 4)
    private String password;
}
