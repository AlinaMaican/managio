package ro.esolutions.eipl.ut.controllers

import org.springframework.http.ResponseEntity
import ro.esolutions.eipl.controllers.EquipmentController
import ro.esolutions.eipl.generator.EquipmentModelGenerator
import ro.esolutions.eipl.services.EquipmentService
import spock.lang.Specification

class EquipmentControllerSpec extends Specification{

    def equipmentService = Mock(EquipmentService)
    def equipmentController = new EquipmentController(equipmentService)

    def getAllEquipments() {
        given:
        def equipmentModelList = [EquipmentModelGenerator.anEquipmentModel()]

        when:
        def result = equipmentController.getAllEquipments()

        then:
        result == ResponseEntity.ok(equipmentModelList)

        and:
        1 * equipmentService.getAllEquipments() >> equipmentModelList
        0 * _
    }
}
