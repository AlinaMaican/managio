package ro.esolutions.eipl.ut.services

import org.springframework.mock.web.MockMultipartFile
import ro.esolutions.eipl.repositories.EquipmentRepository
import ro.esolutions.eipl.services.EquipmentService
import spock.lang.Specification

import static ro.esolutions.eipl.generator.EquipmentGenerator.anEquipment

class EquipmentServiceSpec extends Specification {
    def equipmentRepository = Mock(EquipmentRepository)
    def equipmentService = new EquipmentService(equipmentRepository)

    def "getAllEquipments"() {
        given:

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