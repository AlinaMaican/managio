package ro.esolutions.eipl.ut.services

import org.springframework.mock.web.MockMultipartFile
import ro.esolutions.eipl.repositories.EmployeeRepository
import ro.esolutions.eipl.services.EmployeeService
import spock.lang.Specification

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

    def "uploadEmployeeFromCSV"() {
        given:
        def file = new MockMultipartFile("testUploadEmployeeFile.csv",
                this.getClass().getResourceAsStream("/testUploadEmployeeFile.csv"))
        def employeeList = [aEmployee(id: null)]

        when:
        employeeService.uploadEmployeeFromCSV(file)

        then:
        1 * employeeRepository.saveAll(employeeList)
        0 * _
    }
}
