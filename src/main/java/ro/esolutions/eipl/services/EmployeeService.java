package ro.esolutions.eipl.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ro.esolutions.eipl.entities.Employee;
import ro.esolutions.eipl.exceptions.EmployeeUploadFileNotValid;
import ro.esolutions.eipl.mappers.EmployeeMapper;
import ro.esolutions.eipl.models.EmployeeModel;
import ro.esolutions.eipl.repositories.EmployeeRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    @NonNull
    private final EmployeeRepository employeeRepository;

    public List<EmployeeModel> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }


    public List<EmployeeModel> getFilteredEmployees(String searchValue){
        List<EmployeeModel> resultEmployees = employeeRepository.findDistinctByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchValue,searchValue)
                .stream()
                .map(EmployeeMapper::fromEntityToModel)
                .collect(Collectors.toList());
        return resultEmployees;
    }

    public void uploadEmployeeFromCSV(final MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT);
            List<Employee> equipmentsToSave = StreamSupport.stream(csvParser.spliterator(), false)
                    .map(record -> {
                        Employee employee = EmployeeMapper.fromRecordToEntity(record);
                        employeeRepository.findByFirstNameAndLastName(employee.getFirstName(), employee.getLastName())
                                .ifPresent(employee1 -> employee.setId(employee1.getId()));
                        return employee;
                    }).collect(Collectors.toList());
            employeeRepository.saveAll(equipmentsToSave);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new EmployeeUploadFileNotValid();
        }
    }

}