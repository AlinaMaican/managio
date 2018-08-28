package ro.esolutions.eipl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserModel {

    private Long id;

    @NotEmpty
    @Size(min = 2)
    private String username;

    @NotEmpty
    @Size(min = 2)
    private String firstName;

    @NotEmpty
    @Size(min = 2)
    private String lastName;

    @NotEmpty
    @Size(min = 2)
    @Pattern(regexp = "^(?=.[a-z])(?=.[!@#$%^&*+=])(?=\\S+$).{8,}$")
    private String password;

    @NotNull
    private UserRole userRole;

    @NotNull
    private Boolean isActive;

    @NotNull
    @Size(min = 5)
    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")
    private String email;
}
