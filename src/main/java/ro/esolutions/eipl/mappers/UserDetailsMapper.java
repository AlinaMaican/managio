package ro.esolutions.eipl.mappers;

import ro.esolutions.eipl.configurations.UserDetailsImpl;
import ro.esolutions.eipl.entities.User;

import java.util.Collections;

public final class UserDetailsMapper {
    private UserDetailsMapper() {
    }

    public static UserDetailsImpl fromUserToUserDetails(final User user) {
        return UserDetailsImpl.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .authorities(Collections.singletonList(user.getUserRole()))
                .enabled(user.getIsActive())
                .build();
    }

}
