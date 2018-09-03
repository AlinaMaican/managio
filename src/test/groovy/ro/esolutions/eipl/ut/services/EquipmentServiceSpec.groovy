package ro.esolutions.eipl.ut.services

import ro.esolutions.eipl.repositories.EquipmentRepository
import ro.esolutions.eipl.services.EquipmentService
import spock.lang.Specification


import static ro.esolutions.eipl.generator.EquipmentGenerator.aEquipment
import static ro.esolutions.eipl.generator.EquipmentModelGenerator.aEquipmentModel

class EquipmentServiceSpec extends Specification{

    def equipmentRepository = Mock(EquipmentRepository)
    def equipmentService = new EquipmentService(equipmentRepository)

    def "addNewEquipment"(){
        given:
        def equipmentModel=aEquipmentModel()
        def equipment=aEquipment()

        when:
        def result=equipmentService.addNewEquipment(equipmentModel)

        then:
        result==equipmentModel
        0 * _

        and:
        1 * equipmentRepository.save(equipment) >> equipment
    }

}
