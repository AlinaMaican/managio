package ro.esolutions.eipl.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.esolutions.eipl.entities.EmployeeEquipment;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeEquipmentRepository extends JpaRepository<EmployeeEquipment, Long> {

    List<EmployeeEquipment> findByEndDateLessThan(LocalDate endDate);

    Page<EmployeeEquipment> findByEndDateLessThan(LocalDate endDate, Pageable pageable);

    List<EmployeeEquipment> getEmployeeEquipmentByEmployee_Id(Long employeeId);

    EmployeeEquipment getEmployeeEquipmentById(Long id);

    List<EmployeeEquipment> findByEndDateBetween(LocalDate startDate,LocalDate endDate);
}