package ro.esolutions.eipl.ut.services

import ro.esolutions.eipl.repositories.EquipmentRepository
import ro.esolutions.eipl.services.EquipmentService
import spock.lang.Specification

import static ro.esolutions.eipl.generators.EquipmentGenerator.anEquipment
import static ro.esolutions.eipl.generators.EquipmentModelGenerator.anEquipmentModel

class EquipmentServiceSpec extends Specification {

    def equipmentRepository = Mock(EquipmentRepository)
    def equipmentService = new EquipmentService(equipmentRepository)

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
}
