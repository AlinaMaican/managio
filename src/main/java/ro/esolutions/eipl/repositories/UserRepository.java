package ro.esolutions.eipl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.esolutions.eipl.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
   List<User> findAll();

}
