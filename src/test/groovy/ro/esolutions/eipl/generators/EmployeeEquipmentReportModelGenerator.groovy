package ro.esolutions.eipl.generators

import ro.esolutions.eipl.models.EmployeeEquipmentReportModel

import java.time.LocalDate

import static ro.esolutions.eipl.generators.EmployeeModelGenerator.aEmployeeModel
import static ro.esolutions.eipl.generators.EquipmentModelGenerator.anEquipmentModel

class EmployeeEquipmentReportModelGenerator {

    static aEmployeeEquipmentReportModel(Map overrides = [:]) {
        Map values = [
                lastName : aEmployeeModel().lastName,
                firstName : aEmployeeModel().firstName,
                equipmentName: anEquipmentModel().name,
                equipmentCode: anEquipmentModel().code,
                protectionType: anEquipmentModel().protectionType,
                size: anEquipmentModel().size,
                endDate: LocalDate.of(2018,9,4)
        ]
        values << overrides
        return EmployeeEquipmentReportModel.newInstance(values)
    }
}
