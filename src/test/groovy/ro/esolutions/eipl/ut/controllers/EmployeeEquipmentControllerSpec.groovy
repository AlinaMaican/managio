package ro.esolutions.eipl.ut.controllers

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import ro.esolutions.eipl.controllers.EmployeeEquipmentController
import ro.esolutions.eipl.services.EmployeeEquipmentService
import spock.lang.Specification

import static org.springframework.http.ResponseEntity.ok
import static ro.esolutions.eipl.generators.EmployeeEquipmentModelGenerator.aEmployeeEquipmentModel
import static ro.esolutions.eipl.generators.EmployeeEquipmentReportModelGenerator.aEmployeeEquipmentReportModel

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

    def "getAllEmployeesEquipmentsReport"() {
        when:
        def result = employeeEquipmentController.getAllEmployeesEquipmentsReport()


        then:
        result == ok([aEmployeeEquipmentReportModel()])

        and:
        1 * employeeEquipmentService.getAllEmployeesEquipmentsReport() >> [aEmployeeEquipmentReportModel()]
        0 * _
    }

    def "downloadFile" () {
        given:
        def CSVString = 'test'

        when:
        def result = employeeEquipmentController.downloadFile()

        then:
        result.body == CSVString

        and:
        1 * employeeEquipmentService.exportCSV() >> CSVString
        0 * _
    }

}
