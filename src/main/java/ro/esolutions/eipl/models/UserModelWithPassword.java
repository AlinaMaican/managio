package ro.esolutions.eipl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public  class UserModelWithPassword implements HasUsernameAndEmail {
    private Long id;

    @NotNull
    @Size(min = 2)
    private String username;

    @NotNull
    @Size(min = 2)
    private String firstName;

    @NotNull
    @Size(min = 2)
    private String lastName;

    @NotNull
    private UserRole userRole;

    @NotNull
    private Boolean isActive;

    @NotNull
    @Email
    @Size(min = 5)
    private String email;

    @NotNull
    @Size(min = 4)
    private String password;
}
