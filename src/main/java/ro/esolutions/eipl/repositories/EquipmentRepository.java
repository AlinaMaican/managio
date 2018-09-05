package ro.esolutions.eipl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.esolutions.eipl.entities.Equipment;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Optional<Equipment> findByCode(String code);

    @Query("SELECT ee.equipment from EmployeeEquipment ee where ee.employee.id=:id")
    List<Equipment> getAllEquipmentByEmployeeId(@Param("id") Long id);
}
