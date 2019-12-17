package ro.esolutions.eipl.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ro.esolutions.eipl.entities.Employee;
import ro.esolutions.eipl.exceptions.EmployeeNotFoundException;
import ro.esolutions.eipl.exceptions.EmployeeUploadFileNotValid;
import ro.esolutions.eipl.mappers.EmployeeMapper;
import ro.esolutions.eipl.models.EmployeeModel;
import ro.esolutions.eipl.repositories.EmployeeDAO;
import ro.esolutions.eipl.repositories.EmployeeRepository;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static ro.esolutions.eipl.mappers.EmployeeMapper.fromEntityToModel;

@Service
@Transactional
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDAO employeeDAO;

    @Autowired
    EntityManager entityManager;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeDAO employeeDAO) {
        this.employeeRepository = employeeRepository;
        this.employeeDAO = employeeDAO;
    }


    public List<EmployeeModel> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public List<EmployeeModel> getFilteredEmployees(String searchValue){
        List<EmployeeModel> resultEmployees = employeeDAO.getEmployees(searchValue);
        return resultEmployees;
    }

    public void uploadEmployeeFromCSV(final MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT);
            List<Employee> employeesToSave = StreamSupport.stream(csvParser.spliterator(), false)
                    .map(record -> {
                        Employee employee = EmployeeMapper.fromRecordToEntity(record);
                        employeeRepository.findByFirstNameAndLastName(employee.getFirstName(), employee.getLastName())
                                .ifPresent(employee1 -> employee.setId(employee1.getId()));
                        return employee;
                    }).collect(Collectors.toList());
            employeeRepository.saveAll(employeesToSave);
        } catch (IOException e) {
            throw new EmployeeUploadFileNotValid();
        }
    }

    public EmployeeModel getEmployeeById(final Long employeeId) {
        return employeeRepository.findById(employeeId).map(employee -> fromEntityToModel(employee)).orElseThrow(() -> new EmployeeNotFoundException());
    }

    public EmployeeModel editEmployeeById(final EmployeeModel employeeModel) {
        final Employee employee = EmployeeMapper.fromModelToEntity(employeeModel);
        return EmployeeMapper.fromEntityToModel(employeeRepository.save(employee));
    }

    public List<Employee> getMe(String searchValue){
            List<Employee> res = entityManager.createNativeQuery("Select e from Employees e where e.first_name like '%" + searchValue + "%';").getResultList();
            System.out.println("res este"+res);
            res.stream().peek(s-> System.out.println("Avem"+s));
            return res;
    }

}