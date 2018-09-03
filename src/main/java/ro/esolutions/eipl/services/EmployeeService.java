package ro.esolutions.eipl.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ro.esolutions.eipl.entities.Employee;
import ro.esolutions.eipl.entities.Equipment;
import ro.esolutions.eipl.mappers.EmployeeMapper;
import ro.esolutions.eipl.models.EmployeeModel;
import ro.esolutions.eipl.repositories.EmployeeRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    @NonNull
    private final EmployeeRepository employeeRepository;

    public List<EmployeeModel> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public List<Employee> uploadEmployeeFromCSV(final MultipartFile file){
        List<Employee> employeeList = new ArrayList<>();
        String line;
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while((line = bufferedReader.readLine()) != null) {
                String[] attributes = line.split(",");
                Employee employee = createEmployeeEntity(attributes);
                employeeList.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        employeeRepository.saveAll(employeeList);
        return employeeList;
    }

    public Employee createEmployeeEntity(final String[] attributes){
        String fistName = attributes[0];
        String lastName = attributes[1];
        String workingStation = attributes[2];
        return new Employee(null, fistName, lastName, workingStation);
    }

}