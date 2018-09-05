package ro.esolutions.eipl.ut.controllers

import org.springframework.http.ResponseEntity
import ro.esolutions.eipl.controllers.EmployeeController
import ro.esolutions.eipl.services.EmployeeService
import ro.esolutions.eipl.services.EquipmentService
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static ro.esolutions.eipl.generators.EmployeeModelGenerator.aEmployeeModel
import static ro.esolutions.eipl.generators.EquipmentModelGenerator.aEquipmentModel

class EmployeeControllerSpec extends Specification {

    def employeeService = Mock(EmployeeService)
    def equipmentService = Mock(EquipmentService)
    @Subject
    def employeeController = new EmployeeController(employeeService, equipmentService)

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

    def 'getAllEquipmentForEmployee'() {
        when:
        def result = employeeController.getAllEquipmentForEmployee(1);
        then:
        result == ResponseEntity.ok([aEquipmentModel()])

        and:
        1 * equipmentService.getAllEquipmentForEmployee(1) >> [aEquipmentModel()]
        0 * _
    }
}
