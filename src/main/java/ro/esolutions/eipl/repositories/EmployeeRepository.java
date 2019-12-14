package ro.esolutions.eipl.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.esolutions.eipl.entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findDistinctByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

    @Query("SELECT em FROM Employee em WHERE em.firstName LIKE %:searchValue% OR em.lastName LIKE %:searchValue%")
    List<Employee> getEmployeesWhereFirstNameLikeAndLastNameLike(@Param("searchValue") String searchValue);


    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);
}
