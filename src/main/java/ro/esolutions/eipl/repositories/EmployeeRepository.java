package ro.esolutions.eipl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.esolutions.eipl.entities.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByLastNameContainingIgnoreCase(String s1);
    List<Employee> findByFirstNameContainingIgnoreCase(String s1);
}
