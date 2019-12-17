package ro.esolutions.eipl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserModel implements HasUsernameAndEmail {

    private Long id;

    @Size(min = 2)
    private String username;

    @Size(min = 2)
    private String firstName;

    @Size(min = 2)
    private String lastName;

    @NotNull
    private UserRole userRole;

    @NotNull
    private Boolean isActive;

    @NotNull
    @Size(min = 5)
    private String email;
}
