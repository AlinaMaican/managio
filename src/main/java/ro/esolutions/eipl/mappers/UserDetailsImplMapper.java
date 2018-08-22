package ro.esolutions.eipl.mappers;

import ro.esolutions.eipl.configurations.UserDetailsImpl;
import ro.esolutions.eipl.entities.User;

import java.util.Collections;

public final class UserDetailsImplMapper {
    private UserDetailsImplMapper() {
    }

    public static UserDetailsImpl fromUser(User user) {
        return UserDetailsImpl.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .grantedAuthorities(Collections.singletonList(user.getUserRole()))
                .enabled(user.getIsActive())
                .build();
    }

}
