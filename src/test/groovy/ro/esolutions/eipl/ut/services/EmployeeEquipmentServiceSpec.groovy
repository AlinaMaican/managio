package ro.esolutions.eipl.ut.services

import ro.esolutions.eipl.repositories.EmployeeEquipmentRepository
import ro.esolutions.eipl.services.EmployeeEquipmentService
import spock.lang.Specification

import static ro.esolutions.eipl.generators.EmployeeEquipmentGenerator.aEmployeeEquipment
import static ro.esolutions.eipl.generators.EmployeeEquipmentModelGenerator.aEmployeeEquipmentModel

class EmployeeEquipmentServiceSpec extends Specification {

    def employeeEquipmentRepository = Mock(EmployeeEquipmentRepository)
    def employeeEquipmentService = new EmployeeEquipmentService(employeeEquipmentRepository)

    def "getAllEmployeesEquipments"() {
        when:
        def result = employeeEquipmentService.getAllEmployeesEquipments()

        then:
        result == [aEmployeeEquipmentModel()]

        and:
        1 * employeeEquipmentRepository.findAll() >> [aEmployeeEquipment()]
        0 * _
    }
}
