package ro.esolutions.eipl.ut.controllers

import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.mock.web.MockMultipartFile
import org.springframework.validation.BindingResult
import ro.esolutions.eipl.controllers.EquipmentController
import ro.esolutions.eipl.generators.EquipmentGenerator
import ro.esolutions.eipl.services.EquipmentService
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static org.springframework.http.ResponseEntity.ok
import static ro.esolutions.eipl.generators.EquipmentModelGenerator.aEquipmentModel

class EquipmentControllerSpec extends Specification {

    def equipmentService = Mock(EquipmentService)
    @Subject
    def equipmentController = new EquipmentController(equipmentService)

    def "addNewEquipment"() {
        given:
        def equipmentModel = aEquipmentModel()
        def bindingResult = Mock(BindingResult)

        when:
        def result = equipmentController.addNewEquipment(equipmentModel, bindingResult)

        then:
        result == expectedResult
        0 * _

        and:
        1 * bindingResult.hasErrors() >> hasErrors
        no1 * equipmentService.addNewEquipment(equipmentModel) >> aEquipmentModel()

        where:
        hasErrors | no1 | expectedResult
        true      | 0   | ResponseEntity.badRequest().body(Collections.singletonMap("error", EquipmentController.BINDING_RESULT_ERROR_MESSAGE))
        false     | 1   | ok(aEquipmentModel())

    }

    def getAllEquipments() {
        given:
        def equipmentModelList = new PageImpl([aEquipmentModel()])

        when:
        def result = equipmentController.getAllEquipments(0, 1)

        then:
        result == ok(equipmentModelList)

        and:
        1 * equipmentService.getAllEquipments(PageRequest.of(0, 1)) >> equipmentModelList
        0 * _
    }

    def "uploadEquipmentFromCSV"() {
        given:
        def file = new MockMultipartFile("testUploadEquipmentFIle.csv",
                this.getClass().getResourceAsStream("/testUploadEquipmentFIle.csv"))
        def equipmentList = [EquipmentGenerator.aEquipment()]

        when:
        def result = equipmentController.uploadEquipmentFromCSV(file)

        then:
        result == ok(EquipmentController.JSON_EMPTY_BODY)

        and:
        1 * equipmentService.uploadEquipmentFromCSV(file)
        0 * _
    }

    @Unroll
    def 'getAllUnusedEquipments'() {
        when:
        def result = equipmentController.getAllUnusedEquipments(mediaType)

        then:
        result == expectedResult

        and:
        1 * equipmentService.getAllUnusedEquipments() >> [aEquipmentModel()]
        0 * _

        where:
        mediaType                            | expectedResult
        new MediaType("text", "csv")         | ok('casca,code123,MABEC_01,cap,S,F')
        new MediaType("application", "json") | ok([aEquipmentModel()])
    }
}