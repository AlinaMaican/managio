package ro.esolutions.eipl.ut.controllers

import org.springframework.http.ResponseEntity
import ro.esolutions.eipl.controllers.EmployeeController
import ro.esolutions.eipl.services.EmployeeService
import spock.lang.Specification
import spock.lang.Unroll

import static ro.esolutions.eipl.generators.EmployeeModelGenerator.aEmployeeModel

class EmployeeControllerSpec extends Specification {

    def employeeService = Mock(EmployeeService)
    def employeeController = new EmployeeController(employeeService)

    @Unroll
    def "getAllEmployees"() {
        when:
        def result = employeeController.getAllEmployees()

        then:
        result == ResponseEntity.ok([aEmployeeModel()])

        and:
        1 * employeeService.getAllEmployees() >> [aEmployeeModel()]
        0 * _

    }
}
