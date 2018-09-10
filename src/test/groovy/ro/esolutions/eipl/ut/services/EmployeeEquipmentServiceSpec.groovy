package ro.esolutions.eipl.ut.services

import ro.esolutions.eipl.entities.EmployeeEquipment
import ro.esolutions.eipl.exceptions.ResourceNotFoundException
import ro.esolutions.eipl.repositories.EmployeeEquipmentRepository
import ro.esolutions.eipl.repositories.EmployeeRepository
import ro.esolutions.eipl.repositories.EquipmentRepository
import ro.esolutions.eipl.services.EmployeeEquipmentService
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

import static java.util.Optional.of
import static ro.esolutions.eipl.generators.EmployeeEquipmentGenerator.aEmployeeEquipment
import static ro.esolutions.eipl.generators.EmployeeEquipmentModelGenerator.aEmployeeEquipmentModel
import static ro.esolutions.eipl.generators.EmployeeEquipmentReportModelGenerator.aEmployeeEquipmentReportModel
import static ro.esolutions.eipl.generators.EmployeeGenerator.aEmployee
import static ro.esolutions.eipl.generators.EquipmentGenerator.anEquipment

class EmployeeEquipmentServiceSpec extends Specification {

    def employeeEquipmentRepository = Mock(EmployeeEquipmentRepository)
    def equipmentRepository = Mock(EquipmentRepository)
    def employeeRepository = Mock(EmployeeRepository)

    @Subject
    def employeeEquipmentService = new EmployeeEquipmentService(employeeEquipmentRepository, equipmentRepository, employeeRepository)

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
        1 * employeeEquipmentRepository.findById(1L) >> of(employeeEquipmentEntity)
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

    def 'deleteEmployeeEquipment'() {
        when:
        employeeEquipmentService.deleteEmployeeEquipmentById(1L)
        then:
        1 * employeeEquipmentRepository.deleteById(1L)
        0 * _
    }

    def 'getExpiringEmployeeEquipmentsReport'() {
        when:
        def result = employeeEquipmentService.getExpiringEmployeeEquipmentsReport()

        then:
        result == [aEmployeeEquipmentReportModel()]

        and:
        1 * employeeEquipmentRepository.findByEndDateLessThan(LocalDate.now().plusDays(8)) >> [aEmployeeEquipment()]
        0 * _
    }

    def 'exportCSV'() {
        when:
        def result = employeeEquipmentService.exportCSV()

        then:
        result == 'firstName,lastName,casca,code123,cap,S,2018-09-04'

        and:
        1 * employeeEquipmentRepository.findByEndDateLessThan(LocalDate.now().plusDays(8)) >> [aEmployeeEquipment()]
        0 * _
    }

    def 'saveAllocatedEquipments'() {
        given:
        def allocatedEmployeesEquipments = [aEmployeeEquipmentModel()]
        def employeeId = 1L
        def equipments = [anEquipment(isAvailable: false)]
        def employeeEquipments = [aEmployeeEquipment(id: null, equipment: anEquipment(isAvailable: false))]

        when:
        employeeEquipmentService.saveAllocatedEquipments(allocatedEmployeesEquipments, employeeId)

        then:
        1 * equipmentRepository.findById(1L) >> of(anEquipment())
        1 * employeeRepository.findById(employeeId) >> of(aEmployee())
        1 * equipmentRepository.saveAll(equipments)
        1 * employeeEquipmentRepository.saveAll(employeeEquipments)
        0 * _
    }
}