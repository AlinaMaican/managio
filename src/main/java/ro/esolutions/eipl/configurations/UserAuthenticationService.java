package ro.esolutions.eipl.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ro.esolutions.eipl.entities.User;
import ro.esolutions.eipl.mappers.UserDetailsImplMapper;
import ro.esolutions.eipl.repositories.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user
                .map(UserDetailsImplMapper::fromUser)
                .orElseThrow(() -> new UnsupportedOperationException("Username not found"));
    }
}
