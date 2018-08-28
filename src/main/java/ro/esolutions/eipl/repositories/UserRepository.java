package ro.esolutions.eipl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.esolutions.eipl.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
    Optional<User> findByUsername(String username);
    List<User> findAllByOrderByIdAsc();
    Optional<User> findFirstByEmail(String email);
}