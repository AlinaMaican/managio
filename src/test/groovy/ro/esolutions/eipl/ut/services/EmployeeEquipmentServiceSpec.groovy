package ro.esolutions.eipl.ut.services

import ro.esolutions.eipl.entities.EmployeeEquipment
import ro.esolutions.eipl.exceptions.ResourceNotFoundException
import ro.esolutions.eipl.repositories.EmployeeEquipmentRepository
import ro.esolutions.eipl.services.EmployeeEquipmentService
import spock.lang.Specification
import spock.lang.Subject

import static ro.esolutions.eipl.generators.EmployeeEquipmentGenerator.aEmployeeEquipment
import static ro.esolutions.eipl.generators.EmployeeEquipmentModelGenerator.aEmployeeEquipmentModel

class EmployeeEquipmentServiceSpec extends Specification {

    def employeeEquipmentRepository = Mock(EmployeeEquipmentRepository)
    @Subject
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

    def 'getAllEquipmentForEmployee'() {
        given:
        def employeeEquipmentList = [aEmployeeEquipment()]
        when:
        def result = employeeEquipmentService.getAllEmployeeEquipmentsForEmployee(1)
        then:
        result == [aEmployeeEquipmentModel()]

        and:
        1 * employeeEquipmentRepository.getEmployeeEquipmentByEmployee_Id(1) >> [aEmployeeEquipment()]
        0 * _
    }

    def 'updateEmployeeEquipment'() {
        given:
        def employeeEquipment = aEmployeeEquipmentModel([id: 1L])
        def employeeEquipmentEntity = aEmployeeEquipment([id: 1L])
        when:
        def result = employeeEquipmentService.updateEmployeeEquipment(employeeEquipment)
        then:
        result == employeeEquipment

        and:
        1 * employeeEquipmentRepository.findById(1L) >> Optional.of(employeeEquipmentEntity)
        1 * employeeEquipmentRepository.save(employeeEquipmentEntity) >> employeeEquipmentEntity
        0 * _
    }

    def 'updateEmployeeEquipmentThrows'() {
        given:
        def employeeEquipment = aEmployeeEquipmentModel([id: 1L])
        when:
        employeeEquipmentService.updateEmployeeEquipment(employeeEquipment)
        then:
        def exception = thrown(ResourceNotFoundException)
        exception.getMessage() == new ResourceNotFoundException(1L, EmployeeEquipment.class.getName()).getMessage()
        and:
        1 * employeeEquipmentRepository.findById(1L) >> Optional.ofNullable(null)
        0 * _

    }
}
