package ro.esolutions.eipl.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.esolutions.eipl.entities.EmployeeEquipment;
import ro.esolutions.eipl.exceptions.ResourceNotFoundException;
import ro.esolutions.eipl.mappers.EmployeeEquipmentMapper;
import ro.esolutions.eipl.mappers.EmployeeEquipmentReportMapper;
import ro.esolutions.eipl.models.EmployeeEquipmentModel;
import ro.esolutions.eipl.models.EmployeeEquipmentReportModel;
import ro.esolutions.eipl.repositories.EmployeeEquipmentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static ro.esolutions.eipl.mappers.EmployeeEquipmentMapper.fromEntityToModel;
import static ro.esolutions.eipl.mappers.EmployeeEquipmentMapper.fromModelToEntity;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeEquipmentService {

    @NonNull
    private final EmployeeEquipmentRepository employeeEquipmentRepository;
    private static Integer DAYS = 8;

    public List<EmployeeEquipmentModel> getAllEmployeesEquipments() {
        return employeeEquipmentRepository.findAll()
                .stream()
                .map(EmployeeEquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public List<EmployeeEquipmentReportModel> getExpiringEmployeeEquipmentsReport() {
        return employeeEquipmentRepository.findByEndDateLessThan(LocalDate.now().plusDays(DAYS))
                .stream()
                .map(EmployeeEquipmentReportMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public List<EmployeeEquipmentModel> getAllEmployeeEquipmentsForEmployee(final Long employeeId) {
        return employeeEquipmentRepository.getEmployeeEquipmentByEmployee_Id(employeeId).stream()
                .map(EmployeeEquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public String exportCSV() {
        return employeeEquipmentRepository.findByEndDateLessThan(LocalDate.now().plusDays(DAYS))
                .stream()
                .map(EmployeeEquipmentReportMapper::fromEntityToString)
                .collect(Collectors.joining("\n"));
    }

    public EmployeeEquipmentModel updateEmployeeEquipment(final EmployeeEquipmentModel employeeEquipment) {
        getByIdOrThrow(employeeEquipment.getId());
        return fromEntityToModel(employeeEquipmentRepository.save(fromModelToEntity(employeeEquipment)));
    }

    private EmployeeEquipment getByIdOrThrow(final long id) {
        return employeeEquipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, EmployeeEquipment.class.getName()));
    }

    public void deleteEmployeeEquipmentById(final long id) {
        employeeEquipmentRepository.deleteById(id);
    }
}
