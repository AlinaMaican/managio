package ro.esolutions.eipl.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.esolutions.eipl.mappers.EmployeeMapper;
import ro.esolutions.eipl.models.EmployeeModel;
import ro.esolutions.eipl.repositories.EmployeeRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeModel> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }


    public Set<EmployeeModel> getFilteredEmployees(String searchValue){
        List<EmployeeModel> lastNameEmployees=employeeRepository.findByLastNameContainingIgnoreCase(searchValue)
                .stream()
                .map(EmployeeMapper::fromEntityToModel)
                .collect(Collectors.toList());

        List<EmployeeModel> firstNameEmployees=employeeRepository.findByFirstNameContainingIgnoreCase(searchValue)
                .stream()
                .map(EmployeeMapper::fromEntityToModel)
                .collect(Collectors.toList());

        Set<EmployeeModel> resultEmployees=new HashSet<>();
        resultEmployees.addAll(lastNameEmployees);
        resultEmployees.addAll(firstNameEmployees);

        return resultEmployees;
    }

}
