package ro.esolutions.eipl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.esolutions.eipl.entities.EmployeeEquipment;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeEquipmentRepository extends JpaRepository<EmployeeEquipment, Long> {

    List<EmployeeEquipment> findByEndDateLessThan(LocalDate endDate);

    List<EmployeeEquipment> getEmployeeEquipmentByEmployee_Id(Long employeeId);
}