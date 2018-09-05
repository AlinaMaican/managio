package ro.esolutions.eipl.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.esolutions.eipl.entities.EmployeeEquipment;
import ro.esolutions.eipl.mappers.EmployeeEquipmentMapper;
import ro.esolutions.eipl.models.EmployeeEquipmentModel;
import ro.esolutions.eipl.repositories.EmployeeEquipmentRepository;

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

    public List<EmployeeEquipmentModel> getAllEmployeesEquipments() {

        return employeeEquipmentRepository.findAll()
                .stream()
                .map(EmployeeEquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public List<EmployeeEquipmentModel> getAllEmployeeEquipmentsForEmployee(Long employeeId) {
        return employeeEquipmentRepository.getEmployeeEquipmentByEmployee_Id(employeeId).stream()
                .map(EmployeeEquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public EmployeeEquipmentModel saveEmployeeEquipment(EmployeeEquipmentModel employeeEquipment) {
        return fromEntityToModel(employeeEquipmentRepository.save(fromModelToEntity(employeeEquipment)));
    }
}
