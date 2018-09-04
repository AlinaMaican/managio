package ro.esolutions.eipl.ut.controllers

import ro.esolutions.eipl.controllers.EmployeeEquipmentController
import ro.esolutions.eipl.services.EmployeeEquipmentService
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.http.ResponseEntity.ok
import static ro.esolutions.eipl.generators.EmployeeEquipmentModelGenerator.aEmployeeEquipmentModel

class EmployeeEquipmentControllerSpec extends Specification {

    def employeeEquipmentService = Mock(EmployeeEquipmentService)
    def employeeEquipmentController = new EmployeeEquipmentController(employeeEquipmentService)

    @Unroll
    def "getAllEmployeesEquipments"() {
        when:
        def result = employeeEquipmentController.getAllEmployeesEquipments()


        then:
        result == ok([aEmployeeEquipmentModel()])

        and:
        1 * employeeEquipmentService.getAllEmployeesEquipments() >> [aEmployeeEquipmentModel()]
        0 * _
    }

}
