package ro.esolutions.eipl.ut.services


import ro.esolutions.eipl.repositories.EquipmentRepository
import ro.esolutions.eipl.services.EquipmentService
import spock.lang.Specification

import static ro.esolutions.eipl.generators.EquipmentGenerator.anEquipment

class EquipmentServiceSpec extends Specification {
    def equipmentRepository = Mock(EquipmentRepository)
    def equipmentService = new EquipmentService(equipmentRepository)

    def "getAllEquipments"() {
        when:
        equipmentService.getAllEquipments()

        then:
        1 * equipmentRepository.findAll() >> [anEquipment()]

    }
}
