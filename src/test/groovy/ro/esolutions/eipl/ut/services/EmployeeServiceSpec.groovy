package ro.esolutions.eipl.ut.services

import org.springframework.mock.web.MockMultipartFile
import ro.esolutions.eipl.repositories.EmployeeRepository
import ro.esolutions.eipl.services.EmployeeService
import spock.lang.Specification
import spock.lang.Unroll

import static ro.esolutions.eipl.generators.EmployeeGenerator.aEmployee
import static ro.esolutions.eipl.generators.EmployeeModelGenerator.aEmployeeModel


class EmployeeServiceSpec extends Specification {

    def employeeRepository = Mock(EmployeeRepository)
    def employeeService = new EmployeeService(employeeRepository)

    def "getAllEmployees"() {
        when:
        def result = employeeService.getAllEmployees()

        then:
        result == [aEmployeeModel()]

        and:
        1 * employeeRepository.findAll() >> [aEmployee()]
        0 * _
    }

    @Unroll
    def "uploadEmployeeFromCSV"() {
        given:
        def file = new MockMultipartFile("testUploadEmployeeFile.csv",
                this.getClass().getResourceAsStream("/testUploadEmployeeFile.csv"))
        def employee = aEmployee(id: null)
        def employeeList = [aEmployee(id: null)]
        def firstName = aEmployee().getFirstName()
        def lastName = aEmployee().getLastName()

        when:
        employeeService.uploadEmployeeFromCSV(file)

        then:
        1 * employeeRepository.findByFirstNameAndLastName(firstName, lastName) >> Optional.of(employee)
        1 * employeeRepository.saveAll(employeeList)
        0 * _
    }
}
