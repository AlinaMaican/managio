package ro.esolutions.eipl.ut.services

import org.springframework.mock.web.MockMultipartFile
import ro.esolutions.eipl.repositories.EmployeeEquipmentRepository
import ro.esolutions.eipl.repositories.EmployeeRepository
import ro.esolutions.eipl.repositories.EquipmentRepository
import ro.esolutions.eipl.services.EquipmentService
import spock.lang.Specification
import spock.lang.Subject

import static ro.esolutions.eipl.generators.EquipmentGenerator.anEquipment
import static ro.esolutions.eipl.generators.EquipmentModelGenerator.anEquipmentModel

class EquipmentServiceSpec extends Specification {

    def equipmentRepository = Mock(EquipmentRepository)
    def employeeEquipmentRepository = Mock(EmployeeEquipmentRepository)
    def employeeRepository = Mock(EmployeeRepository)

    @Subject
    def equipmentService = new EquipmentService(equipmentRepository, employeeEquipmentRepository, employeeRepository)

    def "addNewEquipment"() {
        given:
        def equipmentModel = anEquipmentModel()
        def equipment = anEquipment()

        when:
        def result = equipmentService.addNewEquipment(equipmentModel)

        then:
        result == equipmentModel
        0 * _

        and:
        1 * equipmentRepository.save(equipment) >> equipment
    }

    def "getAllEquipments"() {
        when:
        equipmentService.getAllEquipments()

        then:
        1 * equipmentRepository.findAll() >> [anEquipment()]
    }

    def "uploadEquipmentFromCSV"() {
        given:
        def file = new MockMultipartFile("testUploadEquipmentFIle.csv",
                this.getClass().getResourceAsStream("/testUploadEquipmentFIle.csv"))
        def equipment = anEquipment()
        def equipmentList = [anEquipment()]
        def code = anEquipment().getCode()

        when:
        equipmentService.uploadEquipmentFromCSV(file)

        then:
        1 * equipmentRepository.findByCode(code) >> Optional.of(equipment)
        1 * equipmentRepository.saveAll(equipmentList)
        0 * _
    }
}
