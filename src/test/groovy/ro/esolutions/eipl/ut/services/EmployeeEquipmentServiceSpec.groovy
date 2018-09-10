package ro.esolutions.eipl.ut.services

import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import ro.esolutions.eipl.entities.EmployeeEquipment
import ro.esolutions.eipl.exceptions.ResourceNotFoundException
import ro.esolutions.eipl.repositories.EmployeeEquipmentRepository
import ro.esolutions.eipl.services.EmployeeEquipmentService
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

import static ro.esolutions.eipl.generators.EmployeeEquipmentGenerator.aEmployeeEquipment
import static ro.esolutions.eipl.generators.EmployeeEquipmentModelGenerator.aEmployeeEquipmentModel

import static ro.esolutions.eipl.generators.EmployeeEquipmentReportModelGenerator.aEmployeeEquipmentReportModel

class EmployeeEquipmentServiceSpec extends Specification {

    def employeeEquipmentRepository = Mock(EmployeeEquipmentRepository)
    @Subject
    def employeeEquipmentService = new EmployeeEquipmentService(employeeEquipmentRepository)

    def "getAllEmployeesEquipments"() {
        when:
        def result = employeeEquipmentService.getAllEmployeeEquipments()

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

    def 'getExpiringEmployeeEquipmentsReportPaginated'() {
        given:
        def page = 0
        def size = 5
        def pageResult = new PageImpl([aEmployeeEquipment()])

        when:
        def result = employeeEquipmentService.getExpiringEmployeeEquipmentsReportPaginated(page, size)

        then:
        result == new PageImpl([aEmployeeEquipmentReportModel()])

        and:
        1 * employeeEquipmentRepository.findByEndDateLessThan(LocalDate.now().plusDays(8), PageRequest.of(page, size)) >> pageResult
        0 * _


    }

}
