package ro.esolutions.eipl.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public List<EmployeeEquipmentModel> getAllEmployeeEquipments() {
        return employeeEquipmentRepository.findAll()
                .stream()
                .map(EmployeeEquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public List<EmployeeEquipmentReportModel> getAssignedEmployeeEquipmentsReport() {
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
        Equipment  equipment = employeeEquipmentRepository.getEmployeeEquipmentById(id).getEquipment();
        equipment.setIsAvailable(true);
        equipmentRepository.save(equipment);
        employeeEquipmentRepository.deleteById(id);
    }

    public List<EmployeeEquipmentModel> getEmployeeEquipmentAboutToExpireThisWeek(){
        LocalDate  startDate = LocalDate.now();
        LocalDate  endDate = LocalDate.now().plusWeeks(1);
        return employeeEquipmentRepository.findByEndDateBetween(startDate,endDate).stream()
                .map(EmployeeEquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public Page<EmployeeEquipmentReportModel> getAssignedEmployeeEquipmentsReportPaginated(final int page, final int size) {
        return employeeEquipmentRepository.findByEndDateLessThan(
                LocalDate.now().plusDays(DAYS_UNTIL_EXPIRES), PageRequest.of(page, size))
                .map(EmployeeEquipmentReportMapper::fromEntityToModel);
    }

    public void saveAllocatedEquipments(final List<EmployeeEquipmentModel> allocatedEmployeesEquipments,
                                        final Long employeeId) {
        final List<Equipment> equipments = new ArrayList<>();
        final List<EmployeeEquipment> employeeEquipments = new ArrayList<>();

        allocatedEmployeesEquipments.forEach(allocateEquipmentModel -> {
            final Optional<Equipment> optionalEquipment =
                    equipmentRepository.findById(allocateEquipmentModel.getEquipment().getId());
            optionalEquipment.ifPresent(equipment -> {
                equipment.setIsAvailable(Boolean.FALSE);
                equipments.add(optionalEquipment.get());
            });

            final EmployeeEquipment employeeEquipment = EmployeeEquipment.builder()
                    .startDate(allocateEquipmentModel.getStartDate())
                    .endDate(allocateEquipmentModel.getEndDate())
                    .employee(employeeRepository.findById(employeeId).get())
                    .equipment(optionalEquipment.get())
                    .build();
            employeeEquipments.add(employeeEquipment);
        });
        equipmentRepository.saveAll(equipments);
        employeeEquipmentRepository.saveAll(employeeEquipments);
    }
}