package ro.esolutions.eipl.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ro.esolutions.eipl.mappers.UserDetailsMapper;
import ro.esolutions.eipl.repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(UserDetailsMapper::fromUserToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
