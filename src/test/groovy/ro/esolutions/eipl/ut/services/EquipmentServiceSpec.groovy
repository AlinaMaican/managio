package ro.esolutions.eipl.ut.services

import org.springframework.mock.web.MockMultipartFile
import ro.esolutions.eipl.repositories.EquipmentRepository
import ro.esolutions.eipl.services.EquipmentService
import spock.lang.Specification

import static ro.esolutions.eipl.generators.EquipmentGenerator.aEquipment
import static ro.esolutions.eipl.generators.EquipmentModelGenerator.aEquipmentModel

class EquipmentServiceSpec extends Specification {
    def equipmentRepository = Mock(EquipmentRepository)
    def equipmentService = new EquipmentService(equipmentRepository)

    def "addNewEquipment"() {
        given:
        def equipmentModel = aEquipmentModel()
        def equipment = aEquipment()

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
        1 * equipmentRepository.findAll() >> [aEquipment()]
    }

    def "uploadEquipmentFromCSV"() {
        given:
        def file = new MockMultipartFile("testUploadEquipmentFIle.csv",
                this.getClass().getResourceAsStream("/testUploadEquipmentFIle.csv"))
        def equipment = aEquipment()
        def equipmentList = [aEquipment()]
        def code = aEquipment().getCode()

        when:
        equipmentService.uploadEquipmentFromCSV(file)

        then:
        1 * equipmentRepository.findByCode(code) >> Optional.of(equipment)
        1 * equipmentRepository.saveAll(equipmentList)
        0 * _
    }

    def 'getAllUnusedEquipments'() {
        when:
        def result = equipmentService.getAllUnusedEquipments()

        then:
        result == [aEquipmentModel(), aEquipmentModel([id: 2L])]

        and:
        1 * equipmentRepository.getAllUnusedEquipments() >> [aEquipment(), aEquipment([id: 2L])]
        0 * _
    }
}
