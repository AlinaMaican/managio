package ro.esolutions.eipl.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.esolutions.eipl.mappers.EmployeeEquipmentMapper;
import ro.esolutions.eipl.models.EmployeeEquipmentModel;
import ro.esolutions.eipl.repositories.EmployeeEquipmentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeEquipmentService {

    private final EmployeeEquipmentRepository employeeEquipmentRepository;

    public List<EmployeeEquipmentModel> getAllEmployeesEquipments() {

        return employeeEquipmentRepository.findAll()
                .stream()
                .map(EmployeeEquipmentMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

}
