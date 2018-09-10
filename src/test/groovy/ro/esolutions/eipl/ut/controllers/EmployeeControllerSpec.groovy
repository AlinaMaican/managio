package ro.esolutions.eipl.ut.controllers

import org.springframework.http.ResponseEntity
import org.springframework.mock.web.MockMultipartFile
import ro.esolutions.eipl.controllers.EmployeeController
import ro.esolutions.eipl.generators.EmployeeGenerator
import ro.esolutions.eipl.services.EmployeeService
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.http.ResponseEntity.ok
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

    @Unroll
    def "getEmployeeById"() {

        given:
        def employeeId = 0L

        when:
        def result = employeeController.getEmployeeById(employeeId)

        then:
        result == ResponseEntity.ok(aEmployeeModel())

        and:
        1 * employeeService.getEmployeeById(employeeId) >> aEmployeeModel()
        0 * _
    }

    @Unroll
    def "editEmployeeById"() {
        given:
        def employeeModel = aEmployeeModel()
        def helmetSize = "helmetSize"
        def clothingSize = "clothingSize"
        def footwearSize = "footwearSize"
        def employeeId = 0

        when:
        def result = employeeController.editEmployeeById(helmetSize,clothingSize,footwearSize,employeeId)

        then:
        result == ResponseEntity.ok(aEmployeeModel())

        and:
        1 * employeeService.getEmployeeById(employeeId) >> aEmployeeModel()
        1 * employeeService.editEmployeeById(employeeModel) >> aEmployeeModel()
        0 * _
    }

    def "uploadEmployeeFromCSV"() {
        given:
        def file = new MockMultipartFile("testUploadEmployeeFile.csv",
                this.getClass().getResourceAsStream("/testUploadEmployeeFile.csv"))

        when:
        def result = employeeController.uploadEmployeeFromCSV(file)

        then:
        result == ok(EmployeeController.JSON_EMPTY_BODY)

        and:
        1 * employeeService.uploadEmployeeFromCSV(file)
        0 * _
    }
    @Unroll
    def "getFilteredEmployees"()
    {
        given:
        def searchValue = 'Nam'
        when:
        def result=employeeController.getFilteredEmployees(searchValue)

        then:
        result == ResponseEntity.ok([aEmployeeModel()])

        and:
        1* employeeService.getFilteredEmployees(searchValue) >> [aEmployeeModel()]
        0*_
    }
}
