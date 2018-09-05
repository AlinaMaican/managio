package ro.esolutions.eipl.generators

import ro.esolutions.eipl.models.EmployeeEquipmentModel

import java.time.LocalDate

import static ro.esolutions.eipl.generators.EmployeeModelGenerator.aEmployeeModel
import static ro.esolutions.eipl.generators.EquipmentModelGenerator.anEquipmentModel

class EmployeeEquipmentModelGenerator {

    static aEmployeeEquipmentModel(Map overrides = [:]) {
        Map values = [
                id       : 0L,
                employee : aEmployeeModel(),
                equipment: anEquipmentModel(),
                startDate: LocalDate.of(2018,8,4),
                endDate: LocalDate.of(2018,9,4)
        ]
        values << overrides
        return EmployeeEquipmentModel.newInstance(values)
    }
}
