package ro.esolutions.eipl.ut.controllers

import ro.esolutions.eipl.controllers.EmployeeEquipmentController
import ro.esolutions.eipl.services.EmployeeEquipmentService
import spock.lang.Specification

import static org.springframework.http.ResponseEntity.ok
import static ro.esolutions.eipl.generators.EmployeeEquipmentModelGenerator.aEmployeeEquipmentModel

class EmployeeEquipmentControllerSpec extends Specification {

    def employeeEquipmentService = Mock(EmployeeEquipmentService)
    def employeeEquipmentController = new EmployeeEquipmentController(employeeEquipmentService)

    def "getAllEmployeesEquipments"() {
        when:
        def result = employeeEquipmentController.getAllEmployeesEquipments()


        then:
        result == ok([aEmployeeEquipmentModel()])

        and:
        1 * employeeEquipmentService.getAllEmployeesEquipments() >> [aEmployeeEquipmentModel()]
        0 * _
    }

    def 'getAllEquipmentForEmployee'() {
        given:
        def employeeEquipmentModels = [aEmployeeEquipmentModel()]
        when:
        def result = employeeEquipmentController.getAllEmployeeEquipmentsForEmployee(1)

        then:
        result == ok(employeeEquipmentModels)

        and:
        1 * employeeEquipmentService.getAllEmployeeEquipmentsForEmployee(1) >> employeeEquipmentModels
        0 * _
    }

    def 'updateEmployeeEquipment'() {
        given:
        def employeeEquipment = aEmployeeEquipmentModel([id: 1L])
        when:
        def result = employeeEquipmentController.updateEmployeeEquipment(1L, employeeEquipment)
        then:
        result == ok(employeeEquipment)

        and:
        1 * employeeEquipmentService.updateEmployeeEquipment(employeeEquipment) >> employeeEquipment
        0 * _
    }

    def 'deleteEmployeeEquipment'() {
        when:
        employeeEquipmentController.deleteEmployeeEquipment(1L)
        then:
        1 * employeeEquipmentService.deleteEmployeeEquipmentById(1L)
        0 * _
    }
}
