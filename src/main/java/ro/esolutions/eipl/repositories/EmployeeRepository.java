package ro.esolutions.eipl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.esolutions.eipl.entities.Employee;

import java.util.List;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByLastNameContainingIgnoreCase(String s1);
    List<Employee> findByFirstNameContainingIgnoreCase(String s1);
    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);

}
