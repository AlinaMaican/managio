package ro.esolutions.eipl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.esolutions.eipl.entities.EmployeeEquipment;

public interface EmployeeEquipmentRepository extends JpaRepository<EmployeeEquipment, Long> {
}
