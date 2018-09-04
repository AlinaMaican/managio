package ro.esolutions.eipl.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ro.esolutions.eipl.entities.Employee;
import ro.esolutions.eipl.mappers.EmployeeMapper;
import ro.esolutions.eipl.models.EmployeeModel;
import ro.esolutions.eipl.repositories.EmployeeRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

//    public EmployeeModel addNewEmployee(final EmployeeModel employeeModel) {
//        return EmployeeMapper.fromEntityToModel(employeeRepository.save(EmployeeMapper.fromModelToEntity(employeeModel)));
//    }

    public List<Employee> uploadEmployeeFromCSV(final MultipartFile file) {
        List<Employee> employeeList = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT);
            for(CSVRecord csvRecord : csvParser) {
                try {
                    Employee employee = new Employee(null, csvRecord.get(0), csvRecord.get(1), csvRecord.get(2));
                    employeeList.add(employee);
                } catch (Exception e) {
                    log.error("Invalid row!", e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        employeeRepository.saveAll(employeeList);
        return employeeList;
    }

}