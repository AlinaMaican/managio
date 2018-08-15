package ro.esolutions.eipl.entities;

import lombok.*;
import ro.esolutions.eipl.models.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "USER_GEN_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_GEN_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String password;

    @NotNull
    @Enumerated(value= EnumType.STRING)
    private UserRole userRole;

    @NotNull
    private Boolean isActive;
}
