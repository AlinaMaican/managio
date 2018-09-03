package ro.esolutions.eipl.ut.controllers

import org.springframework.http.ResponseEntity
<<<<<<< HEAD
import org.springframework.validation.BindingResult
import ro.esolutions.eipl.controllers.EquipmentController
import ro.esolutions.eipl.services.EquipmentService
import spock.lang.Specification

=======

import ro.esolutions.eipl.generators.EquipmentModelGenerator

>>>>>>> master
class EquipmentControllerSpec extends Specification{

    def equipmentService = Mock(EquipmentService)
    def equipmentController = new EquipmentController(equipmentService)

    def "addNewEquipment"(){

        given:
        def equipmentModel=aEquipmentModel()
        def bindingResult=Mock(BindingResult)

        when:
        def result=equipmentController.addNewEquipment(equipmentModel,bindingResult)

        then:
        result == expectedResult
        0 * _

        and:
        1 * bindingResult.hasErrors() >> hasErrors
        no1* equipmentService.addNewEquipment(equipmentModel) >>aEquipmentModel()

        where:
        hasErrors | no1 |expectedResult
        true      | 0   | ResponseEntity.badRequest().body(Collections.singletonMap("error",EquipmentController.BINDING_RESULT_ERROR_MESSAGE))
        false     | 1   | ResponseEntity.ok(aEquipmentModel())

    }


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
