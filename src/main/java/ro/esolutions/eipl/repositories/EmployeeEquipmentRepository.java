package ro.esolutions.eipl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.esolutions.eipl.entities.EmployeeEquipment;

import java.util.List;

public interface EmployeeEquipmentRepository extends JpaRepository<EmployeeEquipment, Long> {
    List<EmployeeEquipment> getEmployeeEquipmentByEmployee_Id(Long employeeId);

    List<EmployeeEquipment> findByEquipmentIdAndEmployeeId(Long equipmentModelId, Long employeeId);
}
