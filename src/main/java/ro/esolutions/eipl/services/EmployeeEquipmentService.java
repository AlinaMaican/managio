package ro.esolutions.eipl.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.esolutions.eipl.entities.Employee;
import ro.esolutions.eipl.entities.EmployeeEquipment;
import ro.esolutions.eipl.entities.Equipment;
import ro.esolutions.eipl.exceptions.ResourceNotFoundException;
import ro.esolutions.eipl.mappers.EmployeeEquipmentMapper;
import ro.esolutions.eipl.mappers.EmployeeEquipmentReportMapper;
import ro.esolutions.eipl.models.EmployeeEquipmentModel;
import ro.esolutions.eipl.models.EmployeeEquipmentReportModel;
import ro.esolutions.eipl.repositories.EmployeeEquipmentRepository;
import ro.esolutions.eipl.repositories.EmployeeRepository;
import ro.esolutions.eipl.repositories.EquipmentRepository;

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
    private static Integer DAYS_UNTIL_EXPIRES = 8;
    private final EquipmentRepository equipmentRepository;
    private final EmployeeRepository employeeRepository;

    public List<EmployeeEquipmentModel> getAllEmployeesEquipments() {

        return employeeEquipmentRepository.findAll()
                .stream()
                .map(EmployeeEquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public List<EmployeeEquipmentReportModel> getExpiringEmployeeEquipmentsReport() {
        return employeeEquipmentRepository.findByEndDateLessThan(LocalDate.now().plusDays(DAYS_UNTIL_EXPIRES))
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
        return employeeEquipmentRepository.findByEndDateLessThan(LocalDate.now().plusDays(DAYS_UNTIL_EXPIRES))
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

    //    public void saveAllocatedEquipments(List<EmployeeEquipmentModel> allocatedEquipments, Long employeeId) {
//        Equipment equipment = equipmentRepository.getById(employeeId);
//        equipment.setIsAvailable(false);
//        equipmentRepository.save(equipment);
//
//        allocatedEquipments.stream().forEach(employeeEquipmentModel -> {
//            EmployeeEquipment employeeEquipment = new EmployeeEquipment();
//
//            employeeEquipment.setStartDate(employeeEquipmentModel.getStartDate());
//            employeeEquipment.setEndDate(employeeEquipmentModel.getEndDate());
//            employeeEquipment.setEmployee(employeeId);
//            employeeEquipment.setEquipment(employeeEquipmentModel.getEquipment().getId());
//
//            employeeEquipmentRepository.save(employeeEquipment);
//        });
//    }
    public void saveAllocatedEquipments(List<EmployeeEquipmentModel> allocatedEquipments, Long employeeId) {
        List<EmployeeEquipment> listOfEntities = allocatedEquipments.stream()
                .map(EmployeeEquipmentMapper::fromModelToEntity)
                .collect(Collectors.toList());
        listOfEntities.stream().forEach(employeeEquipment -> {
            Equipment equipmentEntity = equipmentRepository.findById(employeeEquipment.getId()).get();
            equipmentEntity.setIsAvailable(false);
            equipmentRepository.save(equipmentEntity);
            employeeEquipment.setEquipment(equipmentEntity);

            Employee employeeEntity = employeeRepository.findById(employeeId).get();
            employeeEquipment.setEmployee(employeeEntity);
        });
        employeeEquipmentRepository.saveAll(listOfEntities);
    }
}
